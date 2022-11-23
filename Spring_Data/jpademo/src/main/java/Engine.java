import model.Address;
import model.Employee;
import model.Project;
import model.Town;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;

public class Engine implements Runnable {
    private final EntityManager entityManager;
    private final BufferedReader reader;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        // Ex.2
        //changeCasingEx2();

        /* Ex.3
       try {
            containsEmployeeEx3();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        // Ex.4
        //employeesWithSalaryOver50000Ex4();

        // Ex.5
        //employeesFromDepartmentEx5();

        // Ex.6
        /*try {
            addingNewAddressAndUpdatingEmployeeEx6();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        // Ex.7
        //addressesWithEmployeeCountEx7();

        /*Ex.8
        try {
            getEmployeeWithProject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        // Ex.9
        //findLatest10Projects();

        // Ex.10
        //increaseSalaries();

        /*Ex.11
        try {
            findEmployeesByFirstName();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        // Ex.12
        //employeesMaximumSalaries();

        // Ex.13
        try {
            removeTowns();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void removeTowns() throws IOException {
        System.out.println("Enter town name:");
        String townName = reader.readLine();
        setEmployeeAddressesToNull(entityManager, townName);
        List<Address> deletedAddresses = getDeletedAddresses(entityManager, townName);
        deleteTownWithGivenName(entityManager, townName);
        System.out.printf("%d %s in %s deleted",
                deletedAddresses.size(),
                deletedAddresses.size() > 1 ? "addresses" : "address",
                townName);
    }

    public static List<Address> getDeletedAddresses(EntityManager entityManager, String townName) {
        List<Address> addresses = entityManager.createQuery("SELECT a FROM Address as a " +
                        "WHERE a.town.name = :townName", Address.class)
                .setParameter("townName", townName)
                .getResultList();
        for (Address address : addresses) {
            entityManager.remove(address);
        }
        return addresses;
    }

    private void setEmployeeAddressesToNull(EntityManager entityManager, String townName) {
        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee as e " +
                        "WHERE e.address.town.name = :townName", Employee.class)
                .setParameter("townName", townName)
                .getResultList();
        for (Employee employee : employees) {
            employee.setAddress(null);
        }
    }

    private void deleteTownWithGivenName(EntityManager entityManager, String townName) {
        Town town = entityManager.createQuery("SELECT t FROM Town as t " +
                        "WHERE t.name like :townName", Town.class)
                .setParameter("townName", townName)
                .getSingleResult();
        entityManager.remove(town);
    }

    private void employeesMaximumSalaries() {
        entityManager.createQuery("SELECT e FROM Employee e " +
                        "WHERE e.salary = (SELECT max(em.salary) FROM Employee as em " +
                        "WHERE em.department.name = e.department.name) " +
                        "AND (e.salary <30000 OR e.salary > 70000)" +
                        "GROUP BY e.department.name", Employee.class)
                .getResultStream()
                .forEach(employee -> {
                    System.out.printf("%s %.2f%n",
                            employee.getDepartment().getName(), employee.getSalary());
                });
    }

    private void findEmployeesByFirstName() throws IOException {
        System.out.println("Enter first letters:");
        String firstLetters = reader.readLine();
        entityManager.createQuery("SELECT e FROM Employee e " +
                        "WHERE UPPER(e.firstName) like CONCAT(:firstLetters,'%') ", Employee.class)
                .setParameter("firstLetters", firstLetters)
                .getResultStream()
                .forEach(employee -> {
                    System.out.printf("%s %s - %s - ($%.2f)%n",
                            employee.getFirstName(), employee.getLastName(),
                            employee.getJobTitle(), employee.getSalary());
                });
    }

    private void increaseSalaries() {
        entityManager.getTransaction().begin();
        int affectedRows = entityManager.createQuery("UPDATE Employee e " +
                        "SET e.salary = e.salary*1.12 " +
                        "WHERE e.department.id IN (1,2,4,11)")
                .executeUpdate();
        entityManager.getTransaction().commit();
        System.out.printf("Affected rows: %d%n", affectedRows);
        entityManager.createQuery("SELECT e FROM Employee e " +
                        "WHERE e.department.id IN (1,2,4,11)", Employee.class)
                .getResultStream()
                .forEach(employee -> {
                    System.out.printf("%s %s ($%.2f)%n",
                            employee.getFirstName(), employee.getLastName(), employee.getSalary());
                });
    }

    private void findLatest10Projects() {
        List<Project> projects = entityManager.createQuery("SELECT p FROM Project as p " +
                        "ORDER BY p.name", Project.class)
                .setMaxResults(10)
                .getResultList();
        projects.forEach(project -> {
            System.out.printf("Project name: %s%n" +
                            "\tProject Description: %s%n" +
                            "\tProject Start Date: %s%n" +
                            "\tProject End Date: %s%n",
                    project.getName(), project.getDescription(), project.getStartDate(), project.getEndDate());
        });
    }

    private void getEmployeeWithProject() throws IOException {
        System.out.println("Enter valid employee ID:");
        int employeeId = Integer.parseInt(reader.readLine());
        Employee employee = entityManager.find(Employee.class, employeeId);
        System.out.printf("%s %s - %s%n",
                employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
        employee.getProjects().stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(project -> {
                    System.out.printf("\t%s%n",
                            project.getName());
                });

    }

    private void addressesWithEmployeeCountEx7() {
        List<Address> addresses = entityManager.createQuery("SELECT a FROM Address as a " +
                        "ORDER BY a.employees.size DESC ", Address.class)
                .setMaxResults(10)
                .getResultList();
        addresses.forEach(address -> {
            System.out.printf("%s - %d employees%n",
                    address.getText(), address.getEmployees().size());
        });
    }

    private void addingNewAddressAndUpdatingEmployeeEx6() throws IOException {
        Address address = createAddress("Vitoshka 15");

        System.out.println("Enter id of employee:");
        int employeeId = Integer.parseInt(reader.readLine());

        Employee employee = entityManager
                .find(Employee.class, employeeId);

        entityManager.getTransaction().begin();
        employee.setAddress(address);
        entityManager.getTransaction().commit();
    }

    private Address createAddress(String addressText) {
        Address address = new Address();
        address.setText(addressText);
        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.getTransaction().commit();
        return address;
    }

    private void employeesFromDepartmentEx5() {
        entityManager
                .createQuery("SELECT e FROM Employee e " +
                        "WHERE e.department.name = 'Research and Development' " +
                        "ORDER BY e.salary, e.id", Employee.class)
                .getResultStream()
                .forEach(employee -> {
                    System.out.printf("%s %s from Research and Development - $%.2f%n",
                            employee.getFirstName(), employee.getLastName(), employee.getSalary());
                });

    }

    private void employeesWithSalaryOver50000Ex4() {
        entityManager.createQuery("SELECT e FROM Employee e " +
                        "WHERE e.salary > 50000", Employee.class)
                .getResultStream()
                .map(Employee::getFirstName)
                .forEach(System.out::println);
    }

    private void containsEmployeeEx3() throws IOException {
        System.out.println("Enter employee full name:");
        String fullName = reader.readLine();
        List<Employee> employees = entityManager.createQuery("select e FROM Employee e " +
                        "WHERE CONCAT(e.firstName,' ',e.lastName) = :name ", Employee.class)
                .setParameter("name", fullName)
                .getResultList();
        System.out.println(employees.size() == 0 ? "NO" : "YES");
    }

    private void changeCasingEx2() {
        List<Town> towns = entityManager.createQuery("SELECT t FROM Town t " +
                        "WHERE length(t.name) <= 5 ", Town.class)
                .getResultList();
        entityManager.getTransaction().begin();
        towns.forEach(entityManager::detach);

        for (Town town : towns) {
            town.setName(town.getName().toLowerCase());
        }
        towns.forEach(entityManager::merge);
        entityManager.flush();
        entityManager.getTransaction().commit();
        System.out.println();
    }
}
