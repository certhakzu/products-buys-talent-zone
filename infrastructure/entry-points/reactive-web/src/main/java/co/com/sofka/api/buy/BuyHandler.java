package co.com.sofka.api.buy;

import co.com.sofka.model.buy.Buy;
import co.com.sofka.usecase.buy.createbuy.CreateBuyUseCase;
import co.com.sofka.usecase.buy.deletebuy.DeleteBuyUseCase;
import co.com.sofka.usecase.buy.findallbuys.FindAllBuysUseCase;
import co.com.sofka.usecase.buy.findbuybyid.FindBuyByIdUseCase;
import co.com.sofka.usecase.buy.updatebuy.UpdateBuyUseCase;
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
    private final FindAllBuysUseCase findAllBuysUseCase;
    private final FindBuyByIdUseCase findBuyByIdUseCase;
    private final DeleteBuyUseCase deleteBuyUseCase;
    private final UpdateBuyUseCase updateBuyUseCase;


    public Mono<ServerResponse> listenPOSTCreateBuyUseCase(ServerRequest serverRequest) {

        return  serverRequest.bodyToMono(Buy.class)
                .flatMap(buy -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createBuyUseCase.createBuy(buy), Buy.class));
    }

    public Mono<ServerResponse> listenGETFindAllBuysUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(findAllBuysUseCase.findAllBuys(), Buy.class);
    }

    public Mono<ServerResponse> listenGETFindBuyByIdUseCase(ServerRequest serverRequest) {
        var id = serverRequest.pathVariable("id");

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(findBuyByIdUseCase.findBuyById(id), Buy.class);
    }

    public Mono<ServerResponse> listenDELETEDeleteBuyUseCase(ServerRequest serverRequest){
        var id = serverRequest.pathVariable("id");

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(deleteBuyUseCase.deleteBuy(id), Buy.class);
    }

    public Mono<ServerResponse> listenPUTUpdateBuyUseCase(ServerRequest serverRequest){
        var id = serverRequest.pathVariable("id");

        return serverRequest.bodyToMono(Buy.class)
                .flatMap(buy -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(updateBuyUseCase.updateBuy(id, buy), Buy.class));
    }
}
