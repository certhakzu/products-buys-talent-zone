package co.com.sofka.usecase.updateproduct;

import co.com.sofka.model.product.Product;
import co.com.sofka.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateProductUseCase {
    private final ProductRepository productRepository;

    public Mono<Product> updateProduct(String id, Product product){
        return productRepository.update(id, product);
    }
}
