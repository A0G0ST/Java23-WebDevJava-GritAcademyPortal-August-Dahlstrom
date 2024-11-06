<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%@ include file="header.jsp" %>
<body>
    <h2>Alla Elever</h2>
    <ul>
        <% 
            List<Map<String, String>> students = (List<Map<String, String>>) request.getAttribute("students");
            if (students != null && !students.isEmpty()) {
                for (Map<String, String> student : students) {
        %>
            <li>
                <%= student.get("name") %> 
                <a href="studentCoursesDetail?username=<%= student.get("username") %>">Visa kurser</a>
            </li>
        <% 
                }
            } else {
        %>
            <p>Inga elever hittades.</p>
        <% } %>
    </ul>
<%@ include file="footer.jsp" %>
