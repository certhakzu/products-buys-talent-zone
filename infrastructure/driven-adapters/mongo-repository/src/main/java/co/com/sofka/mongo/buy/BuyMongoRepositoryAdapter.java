package co.com.sofka.mongo.buy;

import co.com.sofka.model.buy.Buy;
import co.com.sofka.model.buy.gateways.BuyRepository;
import co.com.sofka.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BuyMongoRepositoryAdapter extends AdapterOperations<Buy, BuyDocument, String, BuyMongoDBRepository>
implements BuyRepository
{

    public BuyMongoRepositoryAdapter(BuyMongoDBRepository repository, ObjectMapper mapper) {

        super(repository, mapper, d -> mapper.map(d, Buy.class));
    }
}
