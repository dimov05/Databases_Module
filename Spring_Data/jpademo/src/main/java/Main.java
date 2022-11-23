import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        Engine engine = new Engine(entityManager);
        engine.run();
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine())
    }
}
