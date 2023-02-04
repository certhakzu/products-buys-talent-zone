package co.com.sofka.usecase.product.updateproduct;

import co.com.sofka.model.product.Product;
import co.com.sofka.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class UpdateProductUseCase {
    private final ProductRepository productRepository;
    private static final Logger logger = Logger.getLogger("co.com.sofka.usecase.product.findproductbyid.UpdateProductUseCase");

    public Mono<Product> updateProduct(String id, Product product){

        var isValid = (product.getInInventory() < 0) || (product.getMin() >= product.getMax());

        var valid = Flux.merge(productRepository.isExists(id), Mono.just(isValid));

                     // comprueba si no existe o si los datos no son validos
        return valid.filter(aBoolean -> aBoolean.equals(Boolean.FALSE))
                .doOnNext(aBoolean -> logger.log(Level.INFO, "ELEMENTO: ".concat(String.valueOf(aBoolean))))
                .switchIfEmpty(valid1 -> { // si el filter devuelte un VACIO significa que el elemento si existe y es valido
                    productRepository.update(id, product);
                }).map(aBoolean -> Mono.just(product))
                .reduce((productMono, productMono2) -> productMono)
                .flatMap(productMono -> productMono)
                .log();
    }
}
