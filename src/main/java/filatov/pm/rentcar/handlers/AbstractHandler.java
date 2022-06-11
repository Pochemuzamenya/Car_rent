package filatov.pm.rentcar.handlers;

import filatov.pm.rentcar.DAO.serviceImpl.reactive.ReactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.lang.reflect.ParameterizedType;

@Service
public abstract class AbstractHandler<T> implements Handler {

    final Class<T> typeParameterClass = ((Class) ((ParameterizedType) getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0]);;

    private ReactiveService<T> service;

    @Autowired
    public AbstractHandler(ReactiveService<T> service) {
        this.service = service;
    }

    Class<T> getType() {
        return typeParameterClass;
    }

    @Override
    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), typeParameterClass);
    }

    @Override
    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<T> tMono = request.bodyToMono(typeParameterClass);
        return
                tMono
                        .flatMap(t -> ServerResponse
                                .status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(service.save(t), typeParameterClass)
                        );
    }

    @Override
    public Mono<ServerResponse> delete(ServerRequest request) {
        return service.delete(Integer.valueOf(request.pathVariable("id")))
                .flatMap(t -> ServerResponse.ok().body(t, typeParameterClass))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
