package filatov.pm.rentcar.entity.car.builder;

import filatov.pm.rentcar.entity.car.Car;
import filatov.pm.rentcar.entity.car.CarState;

public interface CarBuilder {

    CarBuilder reset();
    CarBuilder setupModel(String model);

    CarBuilder setupMark(String mark);

    CarBuilder setupReleaseDate(Integer year);

    CarBuilder setupCarBody(String body);

    CarBuilder setupRentalPrice(Double price);

    CarBuilder setupOkCarStatus();

    CarBuilder setupNot_OkCarStatus();

    CarBuilder setupVacantCarState();

    CarBuilder setupBrokenCarState();

    Car build();
}
