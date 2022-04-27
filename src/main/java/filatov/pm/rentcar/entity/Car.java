package filatov.pm.rentcar.entity;

import lombok.Data;

@Data
public class Car {
    private Integer id;
    private String model;
    private String mark;
    private Integer releaseDate;
    private String carBody;
    private Double rentalPrice;
    private Status status;
}
