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

public class CourseDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String courseId = request.getParameter("courseId");
        List<Map<String, String>> students = new ArrayList<>();
        List<Map<String, String>> teachers = new ArrayList<>();

        String userType = (String) session.getAttribute("userType");
        if (courseId != null && ("teacher".equals(userType) || "student".equals(userType))) {
            try (Connection connection = DatabaseConnection.initializeDatabase()) {
                // Hämta elever kopplade till kursen
                String studentQuery = "SELECT s.fName, s.lName FROM students s " +
                                      "JOIN students_courses sc ON s.id = sc.students_id " +
                                      "WHERE sc.courses_id = ?";
                PreparedStatement studentStatement = connection.prepareStatement(studentQuery);
                studentStatement.setString(1, courseId);
                ResultSet studentResultSet = studentStatement.executeQuery();

                while (studentResultSet.next()) {
                    Map<String, String> studentData = new HashMap<>();
                    studentData.put("name", studentResultSet.getString("fName") + " " + studentResultSet.getString("lName"));
                    students.add(studentData);
                }

                // Hämta lärare kopplade till kursen
                String teacherQuery = "SELECT t.fName, t.lName FROM teachers t " +
                                      "JOIN teachers_courses tc ON t.id = tc.teachers_id " +
                                      "WHERE tc.courses_id = ?";
                PreparedStatement teacherStatement = connection.prepareStatement(teacherQuery);
                teacherStatement.setString(1, courseId);
                ResultSet teacherResultSet = teacherStatement.executeQuery();

                while (teacherResultSet.next()) {
                    Map<String, String> teacherData = new HashMap<>();
                    teacherData.put("name", teacherResultSet.getString("fName") + " " + teacherResultSet.getString("lName"));
                    teachers.add(teacherData);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Ett fel uppstod vid hämtning av kursdetaljer.");
            }
        }

        request.setAttribute("students", students);
        request.setAttribute("teachers", teachers);
        request.setAttribute("courseId", courseId);
        request.getRequestDispatcher("courseDetails.jsp").forward(request, response);
    }
}
