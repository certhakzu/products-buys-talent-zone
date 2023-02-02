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
    //Flux<Product> pageBy(Integer amount);
}
