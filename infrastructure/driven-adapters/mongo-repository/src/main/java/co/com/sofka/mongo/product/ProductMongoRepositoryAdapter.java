package co.com.sofka.mongo.product;

import co.com.sofka.model.product.Product;
import co.com.sofka.model.product.gateways.ProductRepository;
import co.com.sofka.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


@Repository
public class ProductMongoRepositoryAdapter extends AdapterOperations<Product, ProductDocument, String, ProductMongoDBRepository>
implements ProductRepository
{

    private static final Logger logger = Logger.getLogger("co.com.sofka.usecase.product.underproduction.ProductMongoRepositoryAdapter");

    public ProductMongoRepositoryAdapter(ProductMongoDBRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Product.class));
    }

    @Override
    public Mono<Product> update(String id, Product product) {
        return repository.save(new ProductDocument(
                id,
                product.getName(),
                product.getInInventory(),
                product.getEnabled(),
                product.getMin(),
                product.getMax()
        )).flatMap(productDocument -> Mono.just(product))
                .onErrorContinue((throwable,o) -> {
                    logger.log(Level.WARNING, "ERROR ACTUALIZANDO PRODUCTO: ".concat(throwable.getMessage().concat(" - ").concat(o.toString())));
                });
    }

    @Override
    public Mono<Boolean> findByName(String name) {
        return repository.findAll()
                .map(product -> product.getName().equalsIgnoreCase(name))
                .filter(aBoolean -> aBoolean.equals(Boolean.TRUE))
                .reduce((aBoolean, aBoolean2) -> aBoolean || aBoolean2)
                .defaultIfEmpty(Mono.just(Boolean.FALSE).block())
                .onErrorContinue((throwable,o) -> {
                    logger.log(Level.WARNING, "ERROR BUSCANDO POR NOMBRE PRODUCTO: ".concat(throwable.getMessage().concat(" - ").concat(o.toString())));
        });
    }

    @Override
    public Mono<Boolean> isExists(String id) {
        return repository.existsById(id)
                .onErrorContinue((throwable,o) -> {
                    logger.log(Level.WARNING, "ERROR : ".concat(throwable.getMessage().concat(" - ").concat(o.toString())));
                });
    }

    @Override
    public Mono<Boolean> isEnabled(String id) {
        return repository.findById(id)
                .map(productDocument -> productDocument.getEnabled())
                .filter(aBoolean -> aBoolean.equals(Boolean.TRUE))
                .defaultIfEmpty(Boolean.FALSE)
                .onErrorContinue((throwable,o) -> {
                    logger.log(Level.WARNING, "ERROR: ".concat(throwable.getMessage().concat(" - ").concat(o.toString())));
                });
    }

    @Override
    public Mono<Boolean> haveStocks(String id, Integer amount) {
        return repository.findById(id)
                .map(productDocument -> productDocument.getInInventory() >= amount)
                .filter(aBoolean -> aBoolean.equals(Boolean.TRUE))
                .switchIfEmpty(Mono.just(Boolean.FALSE));
    }
}
