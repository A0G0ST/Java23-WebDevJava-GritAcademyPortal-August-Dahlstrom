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

public class TeacherCoursesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        List<String> courses = new ArrayList<>();

        if (username != null && "teacher".equals(session.getAttribute("userType"))) {
            try (Connection connection = DatabaseConnection.initializeDatabase()) {
                String query = "SELECT name FROM courses";
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    courses.add(resultSet.getString("name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Ett fel uppstod vid hämtning av kurser.");
            }
        }

        request.setAttribute("courses", courses);
        request.getRequestDispatcher("teacherCourses.jsp").forward(request, response);
    }
}
