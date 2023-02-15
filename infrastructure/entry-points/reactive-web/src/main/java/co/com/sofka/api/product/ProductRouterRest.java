package co.com.sofka.api.product;

import co.com.sofka.api.buy.BuyHandler;
import co.com.sofka.model.buy.Buy;
import co.com.sofka.model.product.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class ProductRouterRest {
@Bean
@RouterOperations(
        {
                @RouterOperation(
                        path = "/api/product/create",
                        produces = {
                                MediaType.APPLICATION_JSON_VALUE
                        },
                        method = RequestMethod.POST,
                        beanClass = ProductHandler.class,
                        beanMethod = "listenPOSTCreateProductUseCase",
                        operation = @Operation(
                                operationId = "listenPOSTCreateProductUseCase",
                                responses = {
                                        @ApiResponse(
                                                responseCode = "200",
                                                description = "successful operation",
                                                content = @Content(schema = @Schema(
                                                        implementation = String.class
                                                ))
                                        ),
                                },
                                requestBody = @RequestBody(
                                        content = @Content(schema = @Schema(
                                                implementation = Product.class
                                        ))
                                )
                        )
                ),
                @RouterOperation(
                        path = "/api/product/findall",
                        produces = {
                                MediaType.APPLICATION_JSON_VALUE
                        },
                        method = RequestMethod.GET,
                        beanClass = ProductHandler.class,
                        beanMethod = "listenGETFindAllProductsUseCase",
                        operation = @Operation(
                                operationId = "listenGETFindAllProductsUseCase",
                                responses = {
                                        @ApiResponse(
                                                responseCode = "200",
                                                description = "successful operation",
                                                content = @Content(schema = @Schema(
                                                        implementation = Product.class
                                                ))
                                        )
                                }
                        )
                ),
                @RouterOperation(
                        path = "/api/product/findbyid/{id}",
                        produces = {
                                MediaType.APPLICATION_JSON_VALUE
                        },
                        method = RequestMethod.GET,
                        beanClass = ProductHandler.class,
                        beanMethod = "listenGETFindProductByIdUseCase",
                        operation = @Operation(
                                operationId = "listenGETFindProductByIdUseCase",
                                responses = {
                                        @ApiResponse(
                                                responseCode = "200",
                                                description = "successful operation",
                                                content = @Content(schema = @Schema(
                                                        implementation = Product.class
                                                ))
                                        ),
                                        @ApiResponse(responseCode = "404", description = "No product found in the search for id.")

                                },
                                parameters = {
                                        @Parameter(in = ParameterIn.PATH, name = "id"),
                                }
                        )
                ),
                @RouterOperation(
                        path = "/api/product/delete/{id}",
                        produces = {
                                MediaType.APPLICATION_JSON_VALUE
                        },
                        method = RequestMethod.DELETE,
                        beanClass = ProductHandler.class,
                        beanMethod = "listenDELETEDeleteProductByIdUseCase",
                        operation = @Operation(
                                operationId = "listenDELETEDeleteProductByIdUseCase",
                                responses = {
                                        @ApiResponse(
                                                responseCode = "200",
                                                description = "successful operation",
                                                content = @Content(schema = @Schema(
                                                        implementation = Buy.class
                                                ))
                                        ),
                                        @ApiResponse(responseCode = "404", description = "No product found in the search for id.")

                                },
                                parameters = {
                                        @Parameter(in = ParameterIn.PATH, name = "id"),
                                }
                        )
                ),
                @RouterOperation(
                        path = "/api/product/update/{id}",
                        produces = {
                                MediaType.APPLICATION_JSON_VALUE
                        },
                        method = RequestMethod.PUT,
                        beanClass = ProductHandler.class,
                        beanMethod = "listenPUTUpdateProductUseCase",
                        operation = @Operation(
                                operationId = "listenPUTUpdateProductUseCase",
                                responses = {
                                        @ApiResponse(
                                                responseCode = "200",
                                                description = "successful operation",
                                                content = @Content(schema = @Schema(
                                                        implementation = Buy.class
                                                ))
                                        ),
                                        @ApiResponse(responseCode = "404", description = "No product found in the search for id.")

                                },
                                parameters = {
                                        @Parameter(in = ParameterIn.PATH, name = "id"),
                                }
                        )
                )
        }
)
public RouterFunction<ServerResponse> productRouterFunction(ProductHandler productHandler) {
    return route(POST("/api/product/create"), request -> productHandler.listenPOSTCreateProductUseCase(request))
            .andRoute(GET("/api/product/findall"), productHandler::listenGETFindAllProductsUseCase)
            .andRoute(DELETE("/api/product/delete/{id}"), productHandler::listenDELETEDeleteProductByIdUseCase)
            .andRoute(GET("/api/product/findbyid/{id}"), productHandler::listenGETFindProductByIdUseCase)
            .andRoute(PUT("/api/product/update/{id}"), productHandler::listenPUTUpdateProductUseCase);

    }
}
