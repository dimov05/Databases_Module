package course.springdata.mapping.models;

import course.springdata.mapping.entities.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ManagerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private List<EmployeeDto> employees;
    private String city;

    private int getSubordinatesCount() {
        return employees.size();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append(": ").append(firstName);
        sb.append(" ").append(lastName);
        sb.append(", City: ").append(city);
        sb.append(", Employees: ").append(getSubordinatesCount()).append("\n");
        sb.append(employees.stream().map(EmployeeDto::toString).collect(Collectors.joining("\n")));
        return sb.toString();
    }
}
