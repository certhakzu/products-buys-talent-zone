package co.com.sofka.usecase.buy.deletebuy;

import co.com.sofka.model.buy.gateways.BuyRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DeleteBuyUseCase {
    private final BuyRepository buyRepository;

    public Mono<Void> deleteBuy(String id){
        return buyRepository.deleteById(id);
    }
}
