package co.com.sofka.model.buy.gateways;

import co.com.sofka.model.buy.Buy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BuyRepository {
    Mono<Buy> save(Buy buy);
    Flux<Buy> findAll();
    //Mono<Buy> findById();
    //Mono<Buy> update(String id, Buy buy);
    Mono<Void> deleteById(String id);
}
