package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CoursesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> courses = new ArrayList<>();

        try (Connection connection = DatabaseConnection.initializeDatabase()) {
            if (connection != null) {
                System.out.println("Databasanslutning lyckades.");
                String query = "SELECT name FROM Courses";
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    courses.add(resultSet.getString("name"));
                }

                System.out.println("Antal kurser hittade: " + courses.size());
                for (String course : courses) {
                    System.out.println("Kursnamn: " + course);
                }
            } else {
                System.out.println("Databasanslutning misslyckades.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ett fel uppstod vid hämtning av kurser.");
        }

        // Skicka kurserna till courses.jsp
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("courses.jsp").forward(request, response);
    }
}
