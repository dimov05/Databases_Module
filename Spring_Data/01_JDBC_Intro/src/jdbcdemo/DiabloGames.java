package jdbcdemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;

public class DiabloGames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username (<Enter> for 'Alex'): ");
        String username = scanner.nextLine().trim();
        // Read params from external property file
        Properties props = new Properties();
        String path = Objects.requireNonNull(DiabloGames.class.getClassLoader()
                .getResource("jdbc.properties")).getPath();
        try {
            props.load(new FileInputStream(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //TODO: Add meaningful defaults

        // 2.Try with resources - connection and preparedStatements

        try (Connection connection = DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.user"),
                props.getProperty("db.password"));
             PreparedStatement ps = connection.prepareStatement(props.getProperty("sql.query"))) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getLong("id") == 0) {
                    System.out.println("No such users exists");
                } else {
                    System.out.printf("| %10d | %-15.15s | %-15.15s | %d |%n",
                            rs.getLong("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getInt("count"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
