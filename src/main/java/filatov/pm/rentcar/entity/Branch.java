package filatov.pm.rentcar.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="branches")
public class Branch {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Size(max = 100)
    private String title;
    @OneToMany
    @JoinColumn(name = "car_id")
    private List<Car> cars;
    @OneToMany
    @JoinColumn(name = "employee_id")
    private Set<Employee> staff;
    @ManyToMany
    private Set<Customer> customers;
    @NotNull
    private City city;
    @OneToMany(mappedBy = "branch")
    private Set<Order> orders;
    private Double balance;

    public void addCar(Car car) {
        this.cars.add(car);
    }

    public void addStaff(Employee employee) {
        this.staff.add(employee);
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
        customer.addBranch(this);
    }

    public void addOrder(Order order) {
        this.orders.add(order);
        order.setBranch(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Branch)) return false;
        Branch branch = (Branch) o;
        return title.equals(branch.title) && Objects.equals(cars, branch.cars) && Objects.equals(staff, branch.staff) && Objects.equals(customers, branch.customers) && city == branch.city && Objects.equals(orders, branch.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, cars, staff, customers, city, orders);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "title = " + title + ", " +
                "city = " + city + ")";
    }
}
