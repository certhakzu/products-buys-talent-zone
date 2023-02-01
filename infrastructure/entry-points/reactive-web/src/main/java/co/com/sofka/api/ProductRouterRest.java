package co.com.sofka.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class ProductRouterRest {
@Bean
public RouterFunction<ServerResponse> routerFunction(ProductHandler handler) {
    return route(POST("/api/product/create"), request -> handler.listenPOSTCreateProductUseCase(request))
            .andRoute(GET("/api/product/findall"), handler::listenGETFindAllProductsUseCase)
            .andRoute(DELETE("/api/product/deletebyid/{id}"), handler::listenDELETEDeleteProductByIdUseCase)
            .andRoute(GET("/api/product/findbyid/{id}"), handler::listenGETFindProductByIdUseCase)
            .andRoute(PUT("/api/product/update/{id}"), handler::listenPUTUpdateProductUseCase);

    }
}
