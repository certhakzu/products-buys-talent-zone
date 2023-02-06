package co.com.sofka.usecase.buy.createbuy;

import co.com.sofka.model.buy.Buy;
import co.com.sofka.model.buy.BuyProducts;
import co.com.sofka.model.buy.gateways.BuyRepository;
import co.com.sofka.model.product.Product;
import co.com.sofka.model.product.gateways.ProductRepository;
import co.com.sofka.usecase.product.updateproduct.UpdateProductUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class CreateBuyUseCase {
    private final BuyRepository buyRepository;
    private final ProductRepository productRepository;
    private final UpdateProductUseCase updateProductUseCase;
    private static final Logger logger = Logger.getLogger("co.com.sofka.usecase.product.underproduction.CreateBuyUseCase");

    public Mono<Buy> createBuy(Buy buy) {
        buy.setDate(Date.from(Instant.now()));

        /*
         * * Verificar tipos de identificacion: CC, CE, NIP, NIT, TI, PAP **LISTO**
         *
         * * Verificar que los productos esten habilitados **LISTO**
         *
         * * Verificar que tengan las existencias solicitadas **LISTO**
         *
         * * Si se cumplen las dos anteriores, se debe crear la compra y reducir las unidades que se compraron
         * */

        //discountStock(buy).log("EJECUTANDO DESCUENTOS DE UNIDADES");

        return   Mono.zip(isIdTypeValid(buy.getIdType()), areEnabled(buy.getBuyProducts()), areStocks(buy.getBuyProducts()))
                // verificar que sea comprable
                .map(objects -> objects.getT1() && objects.getT2() && objects.getT3())
                //realizar la compra o no
                .flatMap(itISPurchasable -> itISPurchasable? buyRepository.save(buy).log("Compra Creada!") : Mono.just(buy).log("No se creó la COMPRA!"));
    }

    private Mono<Boolean> isIdTypeValid(String idType){
        return (!idType.equalsIgnoreCase("CC") && !idType.equalsIgnoreCase("CE")
                && !idType.equalsIgnoreCase("NIP") && !idType.equalsIgnoreCase("NIT")
                && !idType.equalsIgnoreCase("TI") && !idType.equalsIgnoreCase("PAP"))? Mono.just(Boolean.FALSE): Mono.just(Boolean.TRUE);
    }

    private Mono<Boolean> areEnabled(ArrayList<BuyProducts> buyProducts){
        return Flux.fromIterable(buyProducts)
                .map(buyProducts1 -> buyProducts1.getIdProduct())
                .log()
                .flatMap(idProduct -> productRepository.isEnabled(idProduct))
                .filter(aBoolean -> aBoolean.equals(Boolean.FALSE))
                .reduce((aBoolean, aBoolean2) -> aBoolean && aBoolean2)
                .switchIfEmpty(Mono.just(Boolean.TRUE));
    }

    private Mono<Boolean> areStocks(ArrayList<BuyProducts> buyProducts){
        return Flux.fromIterable(buyProducts)
                .filter(buyProducts1 -> {
                    /*var quantityEnabled = productRepository.haveStocks(buyProducts1.getIdProduct(), buyProducts1.getQuantity()).block();
                    return quantityEnabled;*/
                    return Boolean.FALSE.equals(productRepository.haveStocks(buyProducts1.getIdProduct(), buyProducts1.getQuantity()));
                })
                .flatMap(buyProducts1 -> Mono.just(Boolean.FALSE))
                .reduce((aBoolean, aBoolean2) -> aBoolean && aBoolean2)
                .switchIfEmpty(Mono.just(Boolean.TRUE));

                /*.filter(buyProducts1 -> buyProducts1 == null)
                .map(buyProducts1 -> buyProducts1.getQuantity())
                .reduce((integer, integer2) -> integer + integer2)
                .map(integer -> integer < 0)
                .switchIfEmpty(Mono.just(Boolean.TRUE));*/
    }

    private Mono<Buy> discountStock(Buy buy){
        var o = Flux.fromIterable(buy.getBuyProducts())
                .flatMap(buyProducts -> {
                    var product = productRepository.findById(buyProducts.getIdProduct());
                    product.map(product1 -> {
                        var p = new Product(product1.getId(),
                                product1.getName(),
                                product1.getInInventory() - buyProducts.getQuantity(),
                                product1.getEnabled(),
                                product1.getMin(),
                                product1.getMax());
                        return p;
                    });
                    updateProductUseCase.updateProduct(buyProducts.getIdProduct(), Objects.requireNonNull(product.block()));
                    return product;
                }).reduce((product, product2) -> product)
                .flatMap(product -> Mono.just(buy));

        return o;
    }

}
