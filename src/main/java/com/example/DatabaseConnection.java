package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/GritAcademyPortal";
    private static final String USER = "gritwebjava3";
    private static final String PASSWORD = "securepassword789";

    public static Connection initializeDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Kunde inte ansluta: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver-klassen kunde inte hittas: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

  
    public static void main(String[] args) {
        initializeDatabase();
    }
}
