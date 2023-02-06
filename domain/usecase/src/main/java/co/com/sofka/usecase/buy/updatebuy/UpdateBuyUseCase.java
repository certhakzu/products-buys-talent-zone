package co.com.sofka.usecase.buy.updatebuy;

import co.com.sofka.model.buy.Buy;
import co.com.sofka.model.buy.gateways.BuyRepository;
import co.com.sofka.usecase.buy.validateBuy.ValidateBuyUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateBuyUseCase {
    private final BuyRepository buyRepository;
    private final ValidateBuyUseCase validateBuyUseCase;

    public Mono<Buy> updateBuy(String id, Buy buy){
        if (validateBuyUseCase.canBuy(buy).equals(Mono.just(Boolean.TRUE))){
            return buyRepository.update(id, buy);
        } else {
            return Mono.empty();
        }
    }

}
