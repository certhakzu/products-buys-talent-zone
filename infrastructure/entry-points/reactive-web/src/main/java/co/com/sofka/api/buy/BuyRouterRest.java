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
    return route(POST("/api/buy/create"), buyHandler::listenPOSTCreateBuyUseCase);
    //.andRoute(POST("/api/usecase/otherpath"), handler::listenPOSTUseCase).and(route(GET("/api/otherusercase/path"), handler::listenGETOtherUseCase));

    }
}
