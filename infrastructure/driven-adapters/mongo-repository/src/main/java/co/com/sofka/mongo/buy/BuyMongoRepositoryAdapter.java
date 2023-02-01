package co.com.sofka.mongo.buy;

import co.com.sofka.model.buy.Buy;
import co.com.sofka.model.buy.gateways.BuyRepository;
import co.com.sofka.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class BuyMongoRepositoryAdapter extends AdapterOperations<Buy, BuyDocument, String, BuyMongoDBRepository>
implements BuyRepository
{

    public BuyMongoRepositoryAdapter(BuyMongoDBRepository repository, ObjectMapper mapper) {

        super(repository, mapper, d -> mapper.map(d, Buy.class));
    }

    @Override
    public Mono<Buy> update(String id, Buy buy) {
        return repository.save(new BuyDocument(
                id,
                buy.getDate(),
                buy.getIdType(),
                buy.getId(),
                buy.getClientName(),
                buy.getBuyProducts()
        )).flatMap(buyDocument -> Mono.just(buy));
    }
}
