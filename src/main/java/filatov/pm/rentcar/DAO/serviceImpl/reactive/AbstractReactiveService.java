package filatov.pm.rentcar.DAO.serviceImpl.reactive;

import filatov.pm.rentcar.DAO.dao.Dao;
import filatov.pm.rentcar.DAO.serviceImpl.reactive.ReactiveService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public abstract class AbstractReactiveService<T> implements ReactiveService<T> {

    private Dao<T> dao;

    public Mono<T> save(T t){
        dao.save(t);
        return Mono.just(t);
    }

    public Mono<T> findById(Integer id){
        return Mono.just(dao.findById(id).orElseThrow());
    }

    public Mono<T> findByName(String name) {
        return Mono.just(dao.findByName(name).orElseThrow());
    }

    public Flux<T> findAll(){
        return Flux.fromIterable(dao.findAll());
    }

    public Mono<T> delete(Integer id) {
        dao.delete(id);
        return Mono.empty();
    }

    // TODO: 11.06.2022 Add update
}
