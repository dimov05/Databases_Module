package course.springdata.mapping.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private double salary;
    private LocalDate birthday;
    private String city;
    private String managerLastName;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EmployeeDto{");
        sb.append("  - ").append(id);
        sb.append(": ").append(firstName);
        sb.append(" ").append(lastName);
        sb.append(", salary=").append(salary);
        sb.append(", birthday=").append(birthday);
        sb.append(", aCity:").append(city);
        sb.append(", Manager:").append(managerLastName);
        return sb.toString();
    }
}
