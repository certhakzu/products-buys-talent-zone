package co.com.sofka.usecase.product.createproduct;

import co.com.sofka.model.product.Product;
import co.com.sofka.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class CreateProductUseCase {
    private final ProductRepository productRepository;

    public Mono<Product> createProduct(Product product){
        /*return productRepository.findAll()
                .filter(product1 -> (product.getName()
                        .equalsIgnoreCase(product1.getName())) // verificacion del nombre del producto
                ).doOnNext(product1 -> System.out.println("PRODUCTO despues de filter: " + product1.getName()))
                .defaultIfEmpty(product)
                .switchIfEmpty(product1 -> productRepository.save(product))
                .reduce((product1, product2) -> product1);*/
        var isValid = (product.getInInventory() < 0) || (product.getMin() >= product.getMax());
        return productRepository.findByName(product.getName())
                .flatMap(aBoolean -> (aBoolean || !isValid)? Mono.just(product): productRepository.save(product))//Si existe o no es valido, no lo crea
                .onErrorResume(error -> {
                    System.out.println("ERROR: " + error.getMessage());
                    return  Mono.just(product);
                });
    }
}
