package co.com.sofka.mongo.product;

import co.com.sofka.model.product.Product;
import co.com.sofka.model.product.gateways.ProductRepository;
import co.com.sofka.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Objects;


@Repository
public class ProductMongoRepositoryAdapter extends AdapterOperations<Product, ProductDocument, String, ProductMongoDBRepository>
implements ProductRepository
{

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
        )).flatMap(productDocument -> Mono.just(product));
    }

    @Override
    public Mono<Boolean> findByName(String name) {
        return repository.findAll()
                .map(product -> product.getName().equalsIgnoreCase(name))
                .filter(aBoolean -> aBoolean.equals(Boolean.TRUE))
                .reduce((aBoolean, aBoolean2) -> aBoolean || aBoolean2)
                .defaultIfEmpty(Mono.just(Boolean.FALSE).block());
    }

    @Override
    public Mono<Boolean> isExists(String id) {
        return repository.existsById(id);
    }
}
