package JDBC_exercises;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Homework {
    public static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    public static final String MINIONS_TABLE_NAME = "minions_db";
    private Connection connection;

    public Homework() {
        this.scanner = new Scanner(System.in);
    }

    private Scanner scanner;


    public void setConnection(String root, String password) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", root);
        properties.setProperty("password", password);
        this.connection = DriverManager.getConnection(CONNECTION_STRING + MINIONS_TABLE_NAME, properties);
    }

    public void getVillainsNamesEx2() throws SQLException {
        String query = "SELECT v.name, COUNT(mv.minion_id) as count FROM villains as v " +
                "JOIN minions_villains mv on v.id = mv.villain_id GROUP BY v.name " +
                "HAVING count > 15 ORDER BY count DESC;";
        PreparedStatement ps = connection.prepareStatement(query);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.printf("%s %d%n",
                    rs.getString("name"),
                    rs.getInt("count"));
        }
    }

    public void getMinionNamesEx3() throws SQLException {
        System.out.println("Enter Villain id:");
        int villainId = Integer.parseInt(scanner.nextLine());

        String villainName = getEntityNameById(villainId, "villains");
        if (villainName == null) {
            System.out.printf("No villain with ID %d exists in the database.", villainId);
        } else {
            String query = "SELECT m.name, m.age FROM minions AS m " +
                    "JOIN minions_villains mv on m.id = mv.minion_id " +
                    "WHERE mv.villain_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, villainId);
            ResultSet rs = ps.executeQuery();
            int counter = 0;
            System.out.printf("Villain: %s%n", villainName);
            while (rs.next()) {
                counter++;
                System.out.printf("%d. %s %d%n",
                        counter, rs.getString("name"),
                        rs.getInt("age"));
            }

        }


    }

    private String getEntityNameById(int entityId, String tableName) throws SQLException {
        String query = String.format("SELECT name FROM %s WHERE id = ?",
                tableName);
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, entityId);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? rs.getString("name") : null;
    }

    public void addMinionEx4() throws SQLException {
        System.out.println("Enter minions info: name, age, town name:");
        String[] minions = scanner.nextLine().split("\\s+");
        String minionName = minions[0];
        int age = Integer.parseInt(minions[1]);
        String townName = minions[2];
        int townId = getEntityIdByName(minionName, "towns");
        if(townId < 0){
            insertEntityInTowns(townName);
            System.out.printf("%s was added to the database.%n",
                    townName);
        }

    }

    private void insertEntityInTowns(String townName) throws SQLException {
        String query = "INSERT INTO towns(name) value(?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,townName);
        ps.executeUpdate();
    }

    private int getEntityIdByName(String entityName, String tableName) throws SQLException {
        String query = String.format("SELECT id FROM %s WHERE name = ?",
                tableName);
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, entityName);
        ResultSet rs = ps.executeQuery();

        return rs.next() ? rs.getInt("Id") : -1;
    }

    public void changeTownNameCasingEx5() throws SQLException {
        System.out.println("Enter country name:");
        String countryName = scanner.nextLine();
        String query = "UPDATE towns SET name = UPPER(name) WHERE country = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1,countryName);
        System.out.println(ps.executeUpdate() + " towns names were affected.");
    }

    public void increaseAgeWithStoredProcedure() throws SQLException {
        System.out.println("Enter minion id:");
        int minionId = Integer.parseInt(scanner.nextLine());
        String query = "CALL usp_get_older(?)";

        CallableStatement cs = connection.prepareCall(query);
        cs.setInt(1,minionId);
        cs.execute();
    }
}
