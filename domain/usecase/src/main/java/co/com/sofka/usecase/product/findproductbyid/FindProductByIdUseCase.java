package co.com.sofka.usecase.product.findproductbyid;

import co.com.sofka.model.product.Product;
import co.com.sofka.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindProductByIdUseCase {

    private final ProductRepository productRepository;

    public Mono<Product> findProductById(String id){
        return productRepository.findById(id)
                .onErrorContinue((throwable, o) -> System.out.println("ERROR: " + throwable.getMessage()));
    }
}
