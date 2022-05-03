package filatov.pm.rentcar.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private OrderState state;

    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToOne(fetch = FetchType.LAZY)
    private Employee gaveOutEmployee;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    private LocalDateTime gaveOutDate;

    private LocalDateTime refundDate;

    private LocalDateTime factRefundDate;

    private Double deposit;

    private Double payment;

    private Double fine;

    @Enumerated(EnumType.STRING)
    private Status carStatus;
    @ManyToOne
    private Branch branch;

    @OneToOne(fetch = FetchType.LAZY)
    private Employee acceptedEmployee;

    public Order(Car car, Employee gaveOutEmployee, Customer customer, Branch branch, Employee acceptedEmployee) {
        this.state = OrderState.IN_PROGRESS;
        this.car = car;
        this.gaveOutEmployee = gaveOutEmployee;
        this.customer = customer;
        this.gaveOutDate = LocalDateTime.now();
        this.refundDate = this.getGaveOutDate().plusDays(1L);
        this.factRefundDate = null;
        this.deposit = car.getRentalPrice() / 10;
        this.payment = null;
        this.fine = null;
        this.carStatus = car.getStatus();
        this.branch = branch;
        this.acceptedEmployee = acceptedEmployee;
        this.title = car.getMark() + " " + car.getModel() + " " + state.toString() + " " + gaveOutDate.toString() + " " + refundDate.toString() ;
    }

    // TODO: 04.05.2022 refactor: add methods for set fields: gaveOutDate, refundDate, deposit, state, fine, payment

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "state = " + state + ", " +
                "title = " + title + ", " +
                "gaveOutDate = " + gaveOutDate + ", " +
                "refundDate = " + refundDate + ", " +
                "factRefundDate = " + factRefundDate + ", " +
                "deposit = " + deposit + ", " +
                "payment = " + payment + ", " +
                "fine = " + fine + ", " +
                "carStatus = " + carStatus + ", " +
                "branch = " + branch + ")";
    }
}
