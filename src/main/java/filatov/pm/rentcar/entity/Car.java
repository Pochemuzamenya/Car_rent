package filatov.pm.rentcar.entity;

import lombok.*;

import javax.persistence.*;

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
    private String model;
    private String mark;
    private Integer releaseDate;
    private String carBody;
    private Double rentalPrice;
    private Status status;
}
