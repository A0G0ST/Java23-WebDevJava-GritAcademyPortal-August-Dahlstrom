package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean authenticated = false;
        String userType = "";

        try (Connection connection = DatabaseConnection.initializeDatabase()) {
            // Kontrollera om användaren är en student
            String studentQuery = "SELECT * FROM students WHERE username = ? AND password = ?";
            PreparedStatement studentStatement = connection.prepareStatement(studentQuery);
            studentStatement.setString(1, username);
            studentStatement.setString(2, password);
            ResultSet studentResultSet = studentStatement.executeQuery();

            if (studentResultSet.next()) {
                authenticated = true;
                userType = "student";
            }

            // Om användaren inte är en student, kontrollera om det är en lärare
            if (!authenticated) {
                String teacherQuery = "SELECT * FROM teachers WHERE username = ? AND password = ?";
                PreparedStatement teacherStatement = connection.prepareStatement(teacherQuery);
                teacherStatement.setString(1, username);
                teacherStatement.setString(2, password);
                ResultSet teacherResultSet = teacherStatement.executeQuery();

                if (teacherResultSet.next()) {
                    authenticated = true;
                    userType = "teacher";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=db");
            return;
        }

        if (authenticated) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("userType", userType);
            session.setAttribute("stateType", "confirmed");
            response.sendRedirect("userPage.jsp");
        } else {
            response.sendRedirect("login.jsp?error=invalid");
        }
    }
}

