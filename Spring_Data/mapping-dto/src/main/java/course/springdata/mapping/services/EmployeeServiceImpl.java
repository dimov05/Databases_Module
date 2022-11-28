package course.springdata.mapping.services;

import course.springdata.mapping.dao.EmployeeRepository;
import course.springdata.mapping.entities.Employee;
import course.springdata.mapping.exceptions.NonExistingEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional()
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepo.findById(id).orElseThrow(
                () -> new NonExistingEntityException(
                        String.format("Employee with ID=%s does not exist",
                                id)));
    }

    @Override
    public Employee addEmployee(Employee employee) {
        employee.setId(null);
        if (employee.getManager() != null) {
            employee.getManager().getSubordinates().add(employee);
        }
        return employeeRepo.save(employee);
    }

    @Override
    @Transactional
    public Employee updateEmployee(Employee employee) {
        Employee existing = getEmployeeById(employee.getId());
        Employee updated = employeeRepo.save(employee);
        if (existing.getManager() != null && !existing.getManager().equals(employee.getManager())) {
            existing.getManager().getSubordinates().remove(existing);
        }
        if (updated.getManager() != null && !updated.getManager().equals(existing.getManager())) {
            updated.getManager().getSubordinates().add(updated);
        }
        return employeeRepo.save(employee);
    }

    @Override
    public Employee deleteEmployeeById(Long id) {
        Employee removed = getEmployeeById(id);
        if (removed.getManager() != null) {
            removed.getManager().getSubordinates().remove(removed);
        }
        employeeRepo.delete(removed);
        return removed;
    }

    @Override
    public long getEmployeesCount() {
        return employeeRepo.count();
    }

    @Override
    public List<Employee> getAllManagers() {
        return employeeRepo.getManagers();
    }

    @Override
    public List<Employee> getAllEmployeesBornBefore(LocalDate birthday) {
        return this.employeeRepo.findAllByBirthdayBeforeOrderBySalaryDesc(birthday);
    }
}
