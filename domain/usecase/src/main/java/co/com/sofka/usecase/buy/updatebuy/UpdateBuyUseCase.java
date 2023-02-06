package co.com.sofka.usecase.buy.updatebuy;

import co.com.sofka.model.buy.Buy;
import co.com.sofka.model.buy.gateways.BuyRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateBuyUseCase {
    private final BuyRepository buyRepository;

    public Mono<Buy> updateBuy(String id, Buy buy){
        return buyRepository.update(id, buy);
    }

}
