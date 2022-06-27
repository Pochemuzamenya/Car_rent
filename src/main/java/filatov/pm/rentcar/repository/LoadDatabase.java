package filatov.pm.rentcar.repository;

import filatov.pm.rentcar.DAO.serviceImpl.reactive.ReactiveService;
import filatov.pm.rentcar.entity.Branch.Branch;
import filatov.pm.rentcar.entity.car.Car;
import filatov.pm.rentcar.entity.car.builder.CarBuilder;
import filatov.pm.rentcar.entity.car.builder.CarBuilderImpl;
import filatov.pm.rentcar.entity.car.builder.CarDirector;
import filatov.pm.rentcar.entity.customer.Customer;
import filatov.pm.rentcar.entity.customer.CustomerState;
import filatov.pm.rentcar.entity.employee.Employee;
import filatov.pm.rentcar.entity.order.Order;
import filatov.pm.rentcar.entity.order.builder.OrderBuilder;
import filatov.pm.rentcar.entity.order.builder.OrderBuilderImpl;
import filatov.pm.rentcar.entity.order.builder.OrderDirector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LogManager.getLogger();

    @Bean
    CommandLineRunner initDatabase(ReactiveService<Branch> branchService, ReactiveService<Car> carService, ReactiveService<Customer> customerService, ReactiveService<Employee> employeeService, ReactiveService<Order> orderService) {
        return args -> {
            Branch irkutskBranch = new Branch();
            irkutskBranch.setTitle("Иркутский парк проката");
            irkutskBranch.setCity(Branch.City.Иркутск);
            irkutskBranch.setBalance(50000D);

            Branch angarskBranch = new Branch();
            angarskBranch.setTitle("Ангарский парк проката");
            angarskBranch.setCity(Branch.City.Ангарск);
            angarskBranch.setBalance(30000D);

            CarBuilder cb = new CarBuilderImpl();
            CarDirector director = new CarDirector(cb);
            Car prius = director.buildOkVacantCar("Toyota", "Prius Alpha", 2014, "Универсал", 2000D);
            Car camry = director.buildOkVacantCar("Toyota", "Camry", 2012, "Седан", 4000D);
            Car landCruiser = director.buildNot_OkBrokenCar("Toyota", "Land Cruiser", 2010, "Внедорожник");
            Car subaruLegacy = director.buildNot_OkBrokenCar("Subaru", "Legacy", 1995, "Универсал");

            Customer oleg = new Customer();
            oleg.setName("Олег");
            oleg.setState(CustomerState.OK);
            Customer andrey = new Customer();
            andrey.setName("Андрей");
            andrey.setState(CustomerState.NOT_OK);

            Employee katerina = new Employee();
            katerina.setName("Катерина");
            katerina.setLogin("Kate@gmail.com");
            katerina.setPassword("qwerty1");

            Employee anton = new Employee();
            anton.setName("Антон");
            anton.setLogin("toha@yandex.ru");
            anton.setPassword("qwertyuiop1");

            Employee alex = new Employee();
            alex.setName("Алексей");
            alex.setLogin("alex@mail.ru");
            alex.setPassword("asdfg2");

            irkutskBranch.addStaff(katerina);
            irkutskBranch.addStaff(anton);
            angarskBranch.addStaff(alex);

            irkutskBranch.addCustomer(oleg);

            angarskBranch.addCustomer(andrey);

            irkutskBranch.addCar(prius);
            irkutskBranch.addCar(camry);

            OrderBuilder ob = new OrderBuilderImpl();
            OrderDirector orderDirector = new OrderDirector(ob);
            Order order1 = orderDirector.buildOrder(prius, katerina, oleg);
            Order order2 = orderDirector.buildOrder(camry, alex, andrey);

            irkutskBranch.addOrder(order1);
            angarskBranch.addOrder(order2);

            angarskBranch.addCar(landCruiser);
            angarskBranch.addCar(subaruLegacy);


            log.info("Preloading " + employeeService.save(katerina));
            log.info("Preloading " + employeeService.save(alex));
            log.info("Preloading " + employeeService.save(anton));


            log.info("Preloading " + carService.save(prius));
            log.info("Preloading " + carService.save(camry));
            log.info("Preloading " + carService.save(landCruiser));
            log.info("Preloading " + carService.save(subaruLegacy));

            log.info("Preloading " + customerService.save(oleg));
            log.info("Preloading " + customerService.save(andrey));

            log.info("Preloading " + branchService.save(irkutskBranch));
            log.info("Preloading " + branchService.save(angarskBranch));


            log.info("Preloading " + orderService.save(order1));
            log.info("Preloading " + orderService.save(order2));
        };
    }
}
