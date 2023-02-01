package co.com.sofka.api.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class ProductRouterRest {
@Bean
public RouterFunction<ServerResponse> productRouterFunction(ProductHandler productHandler) {
    return route(POST("/api/product/create"), request -> productHandler.listenPOSTCreateProductUseCase(request))
            .andRoute(GET("/api/product/findall"), productHandler::listenGETFindAllProductsUseCase)
            .andRoute(DELETE("/api/product/deletebyid/{id}"), productHandler::listenDELETEDeleteProductByIdUseCase)
            .andRoute(GET("/api/product/findbyid/{id}"), productHandler::listenGETFindProductByIdUseCase)
            .andRoute(PUT("/api/product/update/{id}"), productHandler::listenPUTUpdateProductUseCase);

    }
}
