package course.springdata.mapping.services;

import course.springdata.mapping.entities.Employee;

import java.time.LocalDate;
import java.util.List;


public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee addEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    Employee deleteEmployeeById(Long id);

    long getEmployeesCount();

    List<Employee> getAllManagers();

    List<Employee> getAllEmployeesBornBefore(LocalDate birthday);
}
