package co.com.sofka.usecase.product.deleteproduct;

import co.com.sofka.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DeleteProductUseCase {
    private final ProductRepository productRepository;

    public Mono<Void> deleteProduct(String id){
        return productRepository.deleteById(id);
    }
}
