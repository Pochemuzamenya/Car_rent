package filatov.pm.rentcar.entity.car.builder;

import filatov.pm.rentcar.entity.car.Car;
import filatov.pm.rentcar.entity.car.CarState;

public class CarDirector {

    private CarBuilder builder;

    public CarDirector(CarBuilder builder) {
        this.builder = builder;
    }

    public Car buildOkVacantCar(String model, String mark, Integer releaseDate, String carBody, Double rentalPrice) {

        return builder.reset()
                .setupModel(model)
                .setupMark(mark)
                .setupReleaseDate(releaseDate)
                .setupCarBody(carBody)
                .setupRentalPrice(rentalPrice)
                .setupOkCarStatus()
                .setupVacantCarState()
                .build();
    }

    public Car buildNot_OkBrokenCar(String model, String mark, Integer releaseDate, String carBody) {
        return builder.reset()
                .setupModel(model)
                .setupMark(mark)
                .setupReleaseDate(releaseDate)
                .setupCarBody(carBody)
                .setupNot_OkCarStatus()
                .setupBrokenCarState()
                .build();
    }
}
