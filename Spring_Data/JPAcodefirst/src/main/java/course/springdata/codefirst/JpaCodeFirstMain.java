package course.springdata.codefirst;

import course.springdata.codefirst.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class JpaCodeFirstMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("vehicles");
        EntityManager em = emf.createEntityManager();
        Car car1 = new Car("Audi A8", new BigDecimal(56000), "hybrid", 5);
        Truck truck1 = new Truck("Fuso Canter", new BigDecimal(122222), "hybrid", 5.5);
        Plane plane1 = new Plane("Boeing 747", new BigDecimal(2452312), "gasoline", 120);
        em.getTransaction().begin();
        em.persist(car1);
        em.persist(plane1);
        em.persist(truck1);
        em.getTransaction().commit();

        em.getTransaction().begin();
        PlateNumber car1Plate = new PlateNumber("CB1234AC", car1);
        car1.setPlate(car1Plate);
        em.persist(car1Plate);
        em.persist(car1Plate);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Car car = em.find(Car.class, 1L);
        System.out.printf("Found car1: %s%n", car);
        Truck truck = em.find(Truck.class, 2L);
        System.out.printf("Found truck1: %s%n", truck);
        Plane plane = em.find(Plane.class, 2L);
        System.out.printf("Found plane1: %s%n", plane);
        em.getTransaction().commit();

        em.getTransaction().begin();
        em.createQuery("SELECT v FROM Vehicle v", Vehicle.class)   // WHERE c.name LIKE :name ORDER BY s.name")
//                .setParameter("name", "%")
                .getResultList().forEach(System.out::println);
        em.getTransaction().commit();
    }
}
