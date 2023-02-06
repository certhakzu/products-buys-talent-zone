package co.com.sofka.usecase.pageproductby;

import co.com.sofka.model.buy.gateways.BuyRepository;
import co.com.sofka.model.product.Product;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.List;

@RequiredArgsConstructor
public class PageProductByUseCase {

    private final BuyRepository buyRepository;

    public Flux<List<Product>>  pageProductBy(Integer amount){
        return null;
    }
}
