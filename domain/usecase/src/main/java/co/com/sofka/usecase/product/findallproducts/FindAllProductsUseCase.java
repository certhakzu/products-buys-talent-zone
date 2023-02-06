package co.com.sofka.usecase.product.findallproducts;

import co.com.sofka.model.product.Product;
import co.com.sofka.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class FindAllProductsUseCase {
    private final ProductRepository productRepository;

    public Flux<Product> findAllProducts(){
        return productRepository.findAll()
                .onErrorContinue((throwable, o) -> System.out.println("ERROR: " + throwable.getMessage()));
    }
}
