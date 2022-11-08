package JDBC_exercises;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Homework homework = new Homework();
        homework.setConnection("root","root");

        //homework.getVillainsNamesEx2();
        //homework.getMinionNamesEx3();
        //homework.addMinionEx4();
        //homework.changeTownNameCasingEx5();
        //homework.increaseAgeWithStoredProcedure();
    }
}
