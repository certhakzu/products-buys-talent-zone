package co.com.sofka.usecase.buy.findallbuys;

import co.com.sofka.model.buy.Buy;
import co.com.sofka.model.buy.gateways.BuyRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class FindAllBuysUseCase {

    private final BuyRepository buyRepository;

    public Flux<Buy> findAllBuys(){
        return buyRepository.findAll();
    }
}
