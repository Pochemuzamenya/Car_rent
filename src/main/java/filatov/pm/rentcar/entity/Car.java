package filatov.pm.rentcar.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cars")
public class Car {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull @Size(max = 50)
    private String model;
    @NotNull @Size(max = 50)
    private String mark;
    @NotNull @Size(min = 4)
    private Integer releaseDate;
    @NotNull @Size(max = 50)
    private String carBody;
    @NotNull
    private Double rentalPrice;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private CarState state;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return model.equals(car.model) && mark.equals(car.mark) && releaseDate.equals(car.releaseDate) && carBody.equals(car.carBody) && rentalPrice.equals(car.rentalPrice) && status == car.status && state == car.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, mark, releaseDate, carBody, rentalPrice, status, state);
    }
}
