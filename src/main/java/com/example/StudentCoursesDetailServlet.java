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
import jakarta.servlet.http.HttpSession;

public class StudentCoursesDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        List<String> courses = new ArrayList<>();

        if (username != null && "teacher".equals(session.getAttribute("userType"))) {
            try (Connection connection = DatabaseConnection.initializeDatabase()) {
                String query = "SELECT c.name FROM courses c " +
                               "JOIN students_courses sc ON c.id = sc.courses_id " +
                               "JOIN students s ON sc.students_id = s.id " +
                               "WHERE s.username = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    courses.add(resultSet.getString("name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Ett fel uppstod vid hämtning av kurser för eleven.");
            }
        }

        request.setAttribute("courses", courses);
        request.setAttribute("studentUsername", username);
        request.getRequestDispatcher("studentCoursesDetail.jsp").forward(request, response);
    }
}
