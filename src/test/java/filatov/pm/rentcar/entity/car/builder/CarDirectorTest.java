package filatov.pm.rentcar.entity.car.builder;

import filatov.pm.rentcar.entity.car.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

class CarDirectorTest {

    private static final Logger log = LogManager.getLogger();

    CarBuilder builder = new CarBuilderImpl();

    @Test
    void buildCar() {
        CarDirector director = new CarDirector(builder);
        Car car = director.buildOkVacantCar("Toyota", "Mark2", 1999, "Sedan", 2000D);
        log.info(car + "\nCreated");
        Car brokenCar = director.buildNot_OkBrokenCar("Nissan", "Skyline", 1997, "Sedan");
        log.info(brokenCar + "\nCreated");

    }

}