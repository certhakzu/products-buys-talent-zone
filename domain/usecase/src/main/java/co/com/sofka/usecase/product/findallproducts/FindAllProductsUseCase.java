package co.com.sofka.usecase.product.findallproducts;

import co.com.sofka.model.product.Product;
import co.com.sofka.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class FindAllProductsUseCase {
    private final ProductRepository productRepository;
    private static final Logger logger = Logger.getLogger("co.com.sofka.usecase.product.findproductbyid.FindAllProductsUseCase");

    public Flux<Product> findAllProducts(){
        return productRepository.findAll()
                .onErrorContinue((throwable, o) -> logger.log(Level.INFO, "ERROR: ".concat(throwable.getMessage())));
    }
}
