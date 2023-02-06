package co.com.sofka.usecase.buy.validateBuy;

import co.com.sofka.model.buy.Buy;
import co.com.sofka.model.buy.BuyProducts;
import co.com.sofka.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class ValidateBuyUseCase {

    private final ProductRepository productRepository;

    public Mono<Boolean> canBuy(Buy buy){
        List<BuyProducts> buyProducts = buy.getBuyProducts();
        // Validar si el Producto esta enabled: true



        for (BuyProducts products : buyProducts){
            // El producto está habilitado para su compra???
            var product = productRepository.findById(products.getIdProduct()).block();
            if (product.getEnabled().equals(Boolean.FALSE) || product.getInInventory() < products.getQuantity()) {
                // El producto tiene las unidades requeridas??? // Realizar la Compra y descontar la unidades vendidas

                return Mono.just(Boolean.FALSE);
            }
        }

        return Mono.just(Boolean.TRUE);
    }
}
