package co.com.sofka.usecase.buy.createbuy;

import co.com.sofka.model.buy.Buy;
import co.com.sofka.model.buy.gateways.BuyRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateBuyUseCase {
    private final BuyRepository buyRepository;

    public Mono<Buy> createBuy(Buy buy){
        return buyRepository.save(buy);
    }
}
