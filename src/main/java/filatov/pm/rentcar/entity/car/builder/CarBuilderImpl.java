package filatov.pm.rentcar.entity.car.builder;

import filatov.pm.rentcar.entity.Status;
import filatov.pm.rentcar.entity.car.Car;
import filatov.pm.rentcar.entity.car.CarState;

public class CarBuilderImpl implements CarBuilder{

    private Car car;

    @Override
    public CarBuilder reset() {
        car = new Car();
        return this;
    }

    @Override
    public CarBuilder setupModel(String model) {
        car.setModel(model);
        return this;
    }

    @Override
    public CarBuilder setupMark(String mark) {
        car.setMark(mark);
        return this;
    }

    @Override
    public CarBuilder setupReleaseDate(Integer year) {
        car.setReleaseDate(year);
        return this;
    }

    @Override
    public CarBuilder setupCarBody(String body) {
        car.setCarBody(body);
        return this;
    }

    @Override
    public CarBuilder setupRentalPrice(Double price) {
        car.setRentalPrice(price);
        return this;
    }

    @Override
    public CarBuilder setupOkCarStatus() {
        car.setStatus(Status.OK);
        return this;
    }

    @Override
    public CarBuilder setupNot_OkCarStatus() {
        car.setStatus(Status.NOT_OK);
        return this;
    }

    @Override
    public CarBuilder setupVacantCarState() {
        car.setState(CarState.VACANT);
        return this;
    }

    @Override
    public CarBuilder setupBrokenCarState() {
        car.setState(CarState.BROKEN);
        return this;
    }


    @Override
    public Car build() {
        return this.car;
    }
}
