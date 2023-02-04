package co.com.sofka.usecase.product.createproduct;

import co.com.sofka.model.product.Product;
import co.com.sofka.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.logging.Level;
import java.util.logging.Logger;


@RequiredArgsConstructor
public class CreateProductUseCase {
    private final ProductRepository productRepository;
    private static final Logger logger = Logger.getLogger("co.com.sofka.usecase.product.underproduction.CreateProductUseCase");

    public Mono<Product> createProduct(Product product){
        var isValid = (product.getInInventory() < 0) || (product.getMin() >= product.getMax());
        return productRepository.findByName(product.getName())
                .flatMap(aBoolean -> (aBoolean || !isValid)? Mono.just(product): productRepository.save(product))//Si existe o no es valido, no lo crea
                .onErrorResume(error -> {
                    logger.log(Level.INFO, "ERROR: ".concat(error.getMessage()));
                    return  Mono.just(product);
                });
    }
}
