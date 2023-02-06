package co.com.sofka.mongo.buy;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface BuyMongoDBRepository extends ReactiveMongoRepository<BuyDocument, String>, ReactiveQueryByExampleExecutor<BuyDocument> {
}
