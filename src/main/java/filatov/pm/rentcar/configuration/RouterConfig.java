package filatov.pm.rentcar.configuration;

import filatov.pm.rentcar.handlers.CarHandler;
import filatov.pm.rentcar.handlers.CustomerHandler;
import filatov.pm.rentcar.handlers.EmployeeHandler;
import filatov.pm.rentcar.handlers.OrderHandler;
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
    RouterFunction<ServerResponse> staffRouter(EmployeeHandler handler) {
        return route(GET("/handler/staff").and(accept(MediaType.APPLICATION_JSON)), handler::getAll)
                .andRoute(POST("/handler/staff").and(accept(MediaType.APPLICATION_JSON)), handler::create)
                .andRoute(DELETE("/handler/staff/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::delete);
    }

    @Bean
    RouterFunction<ServerResponse> customerRouter(CustomerHandler handler) {
        return route(GET("/handler/customers").and(accept(MediaType.APPLICATION_JSON)), handler::getAll)
                .andRoute(POST("/handler/customers").and(accept(MediaType.APPLICATION_JSON)) , handler::create)
                .andRoute(DELETE("/handler/customers/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::delete);
    }

    @Bean
    RouterFunction<ServerResponse> carRouter(CarHandler handler) {
        return route(GET("/handler/cars").and(accept(MediaType.APPLICATION_JSON)), handler::getAll)
                .andRoute(POST("/handler/cars").and(accept(MediaType.APPLICATION_JSON)) , handler::create)
                .andRoute(DELETE("/handler/cars/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::delete);
    }

    @Bean
    RouterFunction<ServerResponse> orderRouter(OrderHandler handler) {
        return route(GET("/handler/orders").and(accept(MediaType.APPLICATION_JSON)), handler::getAll)
                .andRoute(POST("/handler/orders").and(accept(MediaType.APPLICATION_JSON)) , handler::create)
                .andRoute(DELETE("/handler/orders/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::delete);
    }
}
