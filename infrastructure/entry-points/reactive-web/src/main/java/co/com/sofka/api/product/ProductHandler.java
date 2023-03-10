package co.com.sofka.api.product;

import co.com.sofka.model.product.Product;
import co.com.sofka.usecase.product.createproduct.CreateProductUseCase;
import co.com.sofka.usecase.product.deleteproduct.DeleteProductUseCase;
import co.com.sofka.usecase.product.findallproducts.FindAllProductsUseCase;
import co.com.sofka.usecase.product.findproductbyid.FindProductByIdUseCase;
import co.com.sofka.usecase.product.updateproduct.UpdateProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class ProductHandler {

    private  final CreateProductUseCase createProductUseCase;
    private final FindAllProductsUseCase findAllProductsUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final FindProductByIdUseCase findProductByIdUseCase;
    private final UpdateProductUseCase updateProductUseCase;

    public Mono<ServerResponse> listenPOSTCreateProductUseCase(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(Product.class)
                .flatMap(product -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createProductUseCase.createProduct(product), Product.class));
    }

    public Mono<ServerResponse> listenGETFindAllProductsUseCase(ServerRequest serverRequest) {

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(findAllProductsUseCase.findAllProducts(), Product.class);
                //.defaultIfEmpty(ServerResponse.notFound().build().block());
    }

    public Mono<ServerResponse> listenDELETEDeleteProductByIdUseCase(ServerRequest serverRequest){

        var id = serverRequest.pathVariable("id");
        return  deleteProductUseCase.deleteProduct(id)
                .then(ServerResponse.noContent().build())
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorContinue((throwable, o) -> System.out.println("ERROR: ".concat(throwable.getMessage()).concat(o.toString())));
        /*ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(deleteProductUseCase.deleteProduct(id), Product.class);*/
    }

    public Mono<ServerResponse> listenGETFindProductByIdUseCase(ServerRequest serverRequest){
        var id = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(findProductByIdUseCase.findProductById(id), Product.class);
    }

    public Mono<ServerResponse> listenPUTUpdateProductUseCase(ServerRequest serverRequest){
        var id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(Product.class)
                .flatMap(product -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(updateProductUseCase.updateProduct(id, product), Product.class));
    }
}
