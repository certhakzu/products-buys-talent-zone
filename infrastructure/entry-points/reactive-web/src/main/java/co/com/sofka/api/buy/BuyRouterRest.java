package co.com.sofka.api.buy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class BuyRouterRest {
@Bean
public RouterFunction<ServerResponse> buyRouterFunction(BuyHandler buyHandler) {
    return route(POST("/api/buy/create"), buyHandler::listenPOSTCreateBuyUseCase)
            .andRoute(GET("/api/buy/findall"), buyHandler::listenGETFindAllBuysUseCase)
            .andRoute(GET("/api/buy/findbyid/{id}"), buyHandler::listenGETFindBuyByIdUseCase)
            .andRoute(DELETE("/api/buy/delete/{id}"), buyHandler::listenDELETEDeleteBuyUseCase)
            .andRoute(PUT("/api/buy/update/{id}"), buyHandler::listenPUTUpdateBuyUseCase);

    }
}
