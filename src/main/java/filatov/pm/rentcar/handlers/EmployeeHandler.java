package filatov.pm.rentcar.handlers;

import filatov.pm.rentcar.DAO.serviceImpl.reactive.ReactiveEmployeeService;
import filatov.pm.rentcar.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeHandler implements Handler{

    private ReactiveEmployeeService service;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Employee.class);
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<Employee> employee = request.bodyToMono(Employee.class);
        return
                employee
                        .flatMap(e -> ServerResponse
                                .status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(service.save(e), Employee.class)
        );
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        return service.delete(Integer.valueOf(request.pathVariable("id")))
                .flatMap(e -> ServerResponse.ok().body(e, Employee.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
