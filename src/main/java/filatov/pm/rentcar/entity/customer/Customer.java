package filatov.pm.rentcar.entity.customer;

import filatov.pm.rentcar.entity.Branch.Branch;
import filatov.pm.rentcar.entity.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull @Size(max = 100)
    private String name;
    @Enumerated(EnumType.STRING)
    private CustomerState state;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();

    public void addOrder(Order order) {
        this.orders.add(order);
        order.setCustomer(this);
    }

    public void removeOrder(Order order) {
        this.orders.remove(order);
        order.setCustomer(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return name.equals(customer.name) && state == customer.state && Objects.equals(orders, customer.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, state, orders);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "state = " + state + ")";
    }
}
