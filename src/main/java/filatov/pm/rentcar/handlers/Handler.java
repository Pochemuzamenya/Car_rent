package filatov.pm.rentcar.handlers;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface Handler {

    Mono<ServerResponse> getAll(ServerRequest request);

    Mono<ServerResponse> create(ServerRequest request);

    Mono<ServerResponse> delete(ServerRequest request);

}
