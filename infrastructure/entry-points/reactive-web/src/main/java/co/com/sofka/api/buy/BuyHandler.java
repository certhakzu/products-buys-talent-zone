package co.com.sofka.api.buy;

import co.com.sofka.model.buy.Buy;
import co.com.sofka.usecase.buy.createbuy.CreateBuyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BuyHandler {

    private final CreateBuyUseCase createBuyUseCase;

    public Mono<ServerResponse> listenPOSTCreateBuyUseCase(ServerRequest serverRequest) {

        return  serverRequest.bodyToMono(Buy.class)
                .flatMap(buy -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createBuyUseCase.createBuy(buy), Buy.class));
    }
/*
    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        // useCase2.logic();
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        // usecase.logic();
        return ServerResponse.ok().bodyValue("");
    }*/
}
