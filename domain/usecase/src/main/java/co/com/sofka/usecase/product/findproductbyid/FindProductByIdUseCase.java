package co.com.sofka.usecase.product.findproductbyid;

import co.com.sofka.model.product.Product;
import co.com.sofka.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class FindProductByIdUseCase {

    private final ProductRepository productRepository;
    private static final Logger logger = Logger.getLogger("co.com.sofka.usecase.product.findproductbyid.FindProductByIdUseCase");

    public Mono<Product> findProductById(String id){
        return productRepository.findById(id)
                .onErrorContinue((throwable, o) -> logger.log(Level.INFO, "ERROR: ".concat(throwable.getMessage())));
    }
}
