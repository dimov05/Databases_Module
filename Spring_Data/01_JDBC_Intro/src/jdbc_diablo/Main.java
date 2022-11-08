package jdbc_diablo;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static String DB_URL = "jdbc:mysql://localhost:3306/diablo";
    public static String DB_QUERY = "SELECT u.user_name, u.first_name, u.last_name, COUNT(ug.game_id) as games_played FROM users AS u " +
            "JOIN users_games AS ug ON u.id = ug.user_id " +
            "WHERE u.user_name = ?";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "root");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, props);
        } catch (SQLException e) {
            System.err.printf("Can not connect to DB '%s'.%n",
                    DB_URL);
            System.exit(0);
        }
        // Read username to search for
        String username = scanner.nextLine();

        try {
            PreparedStatement ps = connection.prepareStatement(DB_QUERY);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.printf("User: %s%n",
                        username);
                System.out.printf("%s %s has played %d%n",
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("games_played"));
            }
        } catch (SQLException e) {
            System.err.println("No such user exists");
        }
    }
}
