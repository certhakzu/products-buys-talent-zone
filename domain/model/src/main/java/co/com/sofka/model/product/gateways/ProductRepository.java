package co.com.sofka.model.product.gateways;

import co.com.sofka.model.product.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {

    Mono<Product> save(Product product);
    Flux<Product> findAll();
    Mono<Product> findById(String id);
    Mono<Void> deleteById(String id);
    Mono<Product> update(String id, Product product);
    Mono<Boolean> findByName(String name);
    Mono<Boolean> isExists(String id);
    Mono<Boolean> isEnabled(String id);

    Mono<Boolean> haveStocks(String id, Integer amount);
    Mono<Product> discount(String id, Integer amount);
}
