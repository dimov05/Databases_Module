package course.springdata.hibernateintro;

import course.springdata.hibernateintro.entity.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

public class HibernateIntroMain {
    public static void main(String[] args) {
        //Create Hibernate cfg
        Configuration cfg = new Configuration();
        cfg.configure();

        //Create SessionFactory
        SessionFactory sf = cfg.buildSessionFactory();

        //Create Session
        Session session = sf.openSession();

        //Persist an entity
        Student student = new Student("Gencho Sokolov");
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();

        //Read entity by ID
        session.beginTransaction();
        session.setHibernateFlushMode(FlushMode.MANUAL);
        Optional<Student> result = session.byId(Student.class).loadOptional(2);
        session.getTransaction().commit();
        System.out.printf("Student with ID: %s", result.orElseGet(() -> new Student("Anonymous")));

        //List all students using HQL
        session.beginTransaction();
        session.createQuery("FROM Student ", Student.class)
                .setFirstResult(2)
                .setMaxResults(3)
                .stream().forEach(System.out::println);
        session.getTransaction().commit();

        session.createQuery("FROM Student WHERE name = :name", Student.class)
                .setParameter("name", "Hristo Georgiev")
                .stream().forEach(System.out::println);
        // Type-safe criteria queries
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> Student_ = query.from(Student.class);
        query.select(Student_).where(cb.like(Student_.get("name"), "D%"));
        session.createQuery(query).getResultStream()
                .forEach(System.out::println);

        //Close session
        session.close();
    }
}
