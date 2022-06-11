package filatov.pm.rentcar.DAO.serviceImpl.reactive;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveService<T> {

    Mono<T> save(T t);

    Mono<T> findById(Integer id);

    Mono<T> findByName(String name);

    Flux<T> findAll();

    Mono<T> delete(Integer id);
}
