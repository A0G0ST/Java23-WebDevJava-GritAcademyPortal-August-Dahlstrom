<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%@ include file="header.jsp" %>
<body>
    <h2>Detaljer för Kurs ID: <%= request.getAttribute("courseId") %></h2>
    <h3>Elever</h3>
    <ul>
        <% 
            List<Map<String, String>> students = (List<Map<String, String>>) request.getAttribute("students");
            if (students != null && !students.isEmpty()) {
                for (Map<String, String> student : students) {
        %>
            <li><%= student.get("name") %></li>
        <% 
                }
            } else {
        %>
            <p>Inga elever registrerade för denna kurs.</p>
        <% } %>
    </ul>

    <h3>Lärare</h3>
    <ul>
        <% 
            List<Map<String, String>> teachers = (List<Map<String, String>>) request.getAttribute("teachers");
            if (teachers != null && !teachers.isEmpty()) {
                for (Map<String, String> teacher : teachers) {
        %>
            <li><%= teacher.get("name") %></li>
        <% 
                }
            } else {
        %>
            <p>Inga lärare tillgängliga för denna kurs.</p>
        <% } %>
    </ul>
<%@ include file="footer.jsp" %>
