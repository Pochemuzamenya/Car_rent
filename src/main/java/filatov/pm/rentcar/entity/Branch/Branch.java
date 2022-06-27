package filatov.pm.rentcar.entity.Branch;

import filatov.pm.rentcar.entity.car.Car;
import filatov.pm.rentcar.entity.customer.Customer;
import filatov.pm.rentcar.entity.employee.Employee;
import filatov.pm.rentcar.entity.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "branches")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Size(max = 100)
    private String title;
    @OneToMany
    @JoinColumn(name = "car_id")
    private List<Car> cars = new ArrayList<>();
    @OneToMany
    @JoinColumn(name = "employee_id")
    private Set<Employee> staff = new HashSet<>();
    @OneToMany
    @JoinColumn(name = "customer_id")
    private Set<Customer> customers = new HashSet<>();
    @NotNull
    @Enumerated(EnumType.STRING)
    private City city;
    @OneToMany(mappedBy = "branch")
    private Set<Order> orders = new HashSet<>();
    private Double balance;

    public void addCar(Car car) {
        this.cars.add(car);
    }

    public void addStaff(Employee employee) {
        this.staff.add(employee);
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public void addOrder(Order order) {
        this.orders.add(order);
        order.setBranch(this);
    }

    public Car findCar(String model, String mark, Integer releaseDate) {
        return this.cars.stream()
                .filter(car -> car.getModel().equals(model))
                .filter(car -> car.getMark().equals(mark))
                .filter(car -> car.getReleaseDate().equals(releaseDate))
                .findFirst()
                .orElseGet(Car::new);
    }

    public Employee findEmployee(String name) {
        return this.staff.stream()
                .filter(employee -> employee.getName().equals(name))
                .findFirst()
                .orElseGet(Employee::new);
    }

    public Customer findCustomer(String name) {
        return this.customers.stream()
                .filter(customer -> customer.getName().equals(name))
                .findFirst().orElseGet(Customer::new);
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

    public enum City {
        Иркутск, Ангарск
    }
}
