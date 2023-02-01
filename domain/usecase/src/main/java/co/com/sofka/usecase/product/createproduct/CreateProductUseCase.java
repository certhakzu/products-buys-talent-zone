package co.com.sofka.usecase.product.createproduct;

import co.com.sofka.model.product.Product;
import co.com.sofka.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class CreateProductUseCase {
    private final ProductRepository productRepository;

    public Mono<Product> createProduct(Product product){
        return productRepository.save(product);
    }
}
