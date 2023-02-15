package co.com.sofka.api.buy;

import co.com.sofka.model.buy.Buy;
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
public class BuyRouterRest {
@Bean
@RouterOperations(
        {
                @RouterOperation(
                        path = "/api/buy/create",
                        produces = {
                                MediaType.APPLICATION_JSON_VALUE
                        },
                        method = RequestMethod.POST,
                        beanClass = BuyHandler.class,
                        beanMethod = "listenPOSTCreateBuyUseCase",
                        operation = @Operation(
                                operationId = "listenPOSTCreateBuyUseCase",
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
                                                implementation = Buy.class
                                        ))
                                )
                        )
                ),
                @RouterOperation(
                        path = "/api/buy/findall",
                        produces = {
                                MediaType.APPLICATION_JSON_VALUE
                        },
                        method = RequestMethod.GET,
                        beanClass = BuyHandler.class,
                        beanMethod = "listenGETFindAllBuysUseCase",
                        operation = @Operation(
                                operationId = "listenGETFindAllBuysUseCase",
                                responses = {
                                        @ApiResponse(
                                                responseCode = "200",
                                                description = "successful operation",
                                                content = @Content(schema = @Schema(
                                                        implementation = Buy.class
                                                ))
                                        )
                                }
                        )
                ),
                @RouterOperation(
                        path = "/api/buy/findbyid/{id}",
                        produces = {
                                MediaType.APPLICATION_JSON_VALUE
                        },
                        method = RequestMethod.GET,
                        beanClass = BuyHandler.class,
                        beanMethod = "listenGETFindBuyByIdUseCase",
                        operation = @Operation(
                                operationId = "listenGETFindBuyByIdUseCase",
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
                        path = "/api/buy/delete/{id}",
                        produces = {
                                MediaType.APPLICATION_JSON_VALUE
                        },
                        method = RequestMethod.DELETE,
                        beanClass = BuyHandler.class,
                        beanMethod = "listenDELETEDeleteBuyUseCase",
                        operation = @Operation(
                                operationId = "listenDELETEDeleteBuyUseCase",
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
                        path = "/api/buy/update/{id}",
                        produces = {
                                MediaType.APPLICATION_JSON_VALUE
                        },
                        method = RequestMethod.PUT,
                        beanClass = BuyHandler.class,
                        beanMethod = "listenPUTUpdateBuyUseCase",
                        operation = @Operation(
                                operationId = "listenPUTUpdateBuyUseCase",
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
public RouterFunction<ServerResponse> buyRouterFunction(BuyHandler buyHandler) {
    return route(POST("/api/buy/create"), buyHandler::listenPOSTCreateBuyUseCase)
            .andRoute(GET("/api/buy/findall"), buyHandler::listenGETFindAllBuysUseCase)
            .andRoute(GET("/api/buy/findbyid/{id}"), buyHandler::listenGETFindBuyByIdUseCase)
            .andRoute(DELETE("/api/buy/delete/{id}"), buyHandler::listenDELETEDeleteBuyUseCase)
            .andRoute(PUT("/api/buy/update/{id}"), buyHandler::listenPUTUpdateBuyUseCase);

    }
}
