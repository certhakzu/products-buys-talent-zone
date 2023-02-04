package co.com.sofka.usecase.product.deleteproduct;

import co.com.sofka.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class DeleteProductUseCase {
    private final ProductRepository productRepository;
    private static final Logger logger = Logger.getLogger("co.com.sofka.usecase.product.findproductbyid.DeleteProductUseCase");

    public Mono<Void> deleteProduct(String id){
        return productRepository.deleteById(id)
                .onErrorContinue((throwable, o) -> logger.log(Level.INFO, "ERROR: ".concat(throwable.getMessage())));
    }
}
