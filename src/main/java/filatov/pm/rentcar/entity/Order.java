package filatov.pm.rentcar.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
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
        stateInProgress();
        this.car = car;
        this.gaveOutEmployee = gaveOutEmployee;
        this.customer = customer;
        this.factRefundDate = null;
        this.payment = null;
        this.fine = null;
        this.carStatus = car.getStatus();
        this.branch = branch;
        this.acceptedEmployee = acceptedEmployee;
    }

    public void setupTitle() {
        this.title = "\n" + "Заказ:"
                + "\n" + car.getMark()
                + " " + car.getModel()
                + " \n" + "Статус: " + state.toString()
                + " \n" + "Дата выдачи: "
                + gaveOutDate.format(DateTimeFormatter.ofPattern("MM/dd/yyy 'at' hh:mm a"))
                + " \n" + "Дата возврата: "
                + refundDate.format(DateTimeFormatter.ofPattern("MM/dd/yyy 'at' hh:mm a")) ;
    }

    public void stateInProgress() {
        this.state = OrderState.IN_PROGRESS;
    }

    public void stateInComplete() {
        this.state = OrderState.COMPLETE;
    }

    public void setupGaveOutDate() {
        this.gaveOutDate = LocalDateTime.now();
    }

    public void setupRefundDate() {
        this.refundDate = this.getGaveOutDate().plusDays(1L);
    }

    public void setupFactRefundDate() {
        this.factRefundDate = LocalDateTime.now().plusDays(3L);
    }

    public void calculateDeposit() {
        this.deposit = car.getRentalPrice() / 10;
    }

    public void closeOrder() {
        stateInComplete();
        if (factRefundDate.isAfter(refundDate)) {
            fine = deposit;
            payment = car.getRentalPrice() + fine;
        } else {
            payment = car.getRentalPrice();
        }
        double balance = this.branch.getBalance() + payment;
        this.branch.setBalance(balance);
    }

    @Override
    public String toString() {
        switch (this.state){
            case COMPLETE -> {
                return getClass().getSimpleName() + "(" +
                        "id = " + id + ", " +
                        "state = " + state + ", " +
                        "gaveOutDate = " + gaveOutDate.format(DateTimeFormatter.ofPattern("MM/dd/yyy 'at' hh:mm a")) + ", " +
                        "refundDate = " + refundDate.format(DateTimeFormatter.ofPattern("MM/dd/yyy 'at' hh:mm a")) + ", " +
                        "factRefundDate = " + factRefundDate.format(DateTimeFormatter.ofPattern("MM/dd/yyy 'at' hh:mm a")) + ", " +
                        "deposit = " + deposit + ", " +
                        "payment = " + payment + ", " +
                        "fine = " + fine + ", " +
                        "carStatus = " + carStatus + ", " +
                        "branch = " + branch + ")";
            }
            case IN_PROGRESS -> {
                return getClass().getSimpleName() + "(" +
                        "id = " + id + ", " +
                        "state = " + state + ", " +
                        "gaveOutDate = " + gaveOutDate.format(DateTimeFormatter.ofPattern("MM/dd/yyy 'at' hh:mm a")) + ", " +
                        "refundDate = " + refundDate.format(DateTimeFormatter.ofPattern("MM/dd/yyy 'at' hh:mm a")) + ", " +
                        "deposit = " + deposit + ", " +
                        "carStatus = " + carStatus + ", " +
                        "branch = " + branch + ")";
            }
            case EXPIRED -> {
                return getClass().getSimpleName() + "(" +
                        "id = " + id + ", " +
                        "state = " + state + ", " +
                        "gaveOutDate = " + gaveOutDate.format(DateTimeFormatter.ofPattern("MM/dd/yyy 'at' hh:mm a")) + ", " +
                        "refundDate = " + refundDate.format(DateTimeFormatter.ofPattern("MM/dd/yyy 'at' hh:mm a")) + ", " +
                        "deposit = " + deposit + ", " +
                        "fine = " + fine + ", " +
                        "carStatus = " + carStatus + ", " +
                        "branch = " + branch + ")";
            }
        }
        return "Error";
    }
}
