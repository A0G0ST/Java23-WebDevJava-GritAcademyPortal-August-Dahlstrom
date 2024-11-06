package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AllStudentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Map<String, String>> students = new ArrayList<>();

        if ("teacher".equals(session.getAttribute("userType"))) {
            try (Connection connection = DatabaseConnection.initializeDatabase()) {
                String query = "SELECT CONCAT(fName, ' ', lName) AS fullName, username FROM students";
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Map<String, String> studentData = new HashMap<>();
                    studentData.put("name", resultSet.getString("fullName"));
                    studentData.put("username", resultSet.getString("username"));
                    students.add(studentData);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Ett fel uppstod vid hämtning av elever.");
            }
        }

        request.setAttribute("students", students);
        request.getRequestDispatcher("allStudents.jsp").forward(request, response);
    }
}
