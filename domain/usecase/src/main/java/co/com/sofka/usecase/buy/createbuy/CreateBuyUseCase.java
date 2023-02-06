package co.com.sofka.usecase.buy.createbuy;

import co.com.sofka.model.buy.Buy;
import co.com.sofka.model.buy.gateways.BuyRepository;
import co.com.sofka.usecase.buy.validateBuy.ValidateBuyUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Date;

@RequiredArgsConstructor
public class CreateBuyUseCase {
    private final BuyRepository buyRepository;
    private final ValidateBuyUseCase validateBuyUseCase;

    public Mono<Buy> createBuy(Buy buy){
        buy.setDate(Date.from(Instant.now()));
        /*if (validateBuyUseCase.canBuy(buy).equals(Mono.just(Boolean.TRUE))){
            return buyRepository.save(buy);
        } else {
            return Mono.empty();
        }*/
        return buyRepository.save(buy).log("Buy CREADA!");
    }

    //BUSCAR SI EXISTE Y SI NO EXISTE, ENTONCES SI SE CREA (usar switchIfEmpty())
}
