package filatov.pm.rentcar.configuration;

import filatov.pm.rentcar.handlers.EmployeeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    RouterFunction<ServerResponse> routes(EmployeeHandler handler) {
        return route(GET("/handler/staff").and(accept(MediaType.APPLICATION_JSON)), handler::getAll)
                .andRoute(POST("/handler/staff").and(accept(MediaType.APPLICATION_JSON)), handler::create)
                .andRoute(DELETE("handler/staff/{userId}").and(accept(MediaType.APPLICATION_JSON)), handler::delete);
    }
}