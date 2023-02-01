package co.com.sofka.usecase.buy.findbuybyid;

import co.com.sofka.model.buy.Buy;
import co.com.sofka.model.buy.gateways.BuyRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindBuyByIdUseCase {
    private final BuyRepository buyRepository;

    public Mono<Buy> findBuyById(String id){
        return buyRepository.findById(id);
    }
}
