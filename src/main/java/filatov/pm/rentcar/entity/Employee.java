package filatov.pm.rentcar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employers")
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull @Size(max = 100)
    private String name;
    @NotNull @Size(max = 100)
    private String login;
    @NotNull @Size(min = 8)
    private  String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(login, employee.login) && Objects.equals(password, employee.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, password);
    }
}
