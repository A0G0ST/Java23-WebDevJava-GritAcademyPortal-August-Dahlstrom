<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ include file="header.jsp" %>
<body>
    <h2>Tillgängliga Kurser</h2>
    <ul>
        <% 
            // Behandla kurser som en ArrayList
            List<String> courses = (List<String>) request.getAttribute("courses");
            if (courses == null) {
                out.println("<p>Attributet 'courses' är null.</p>");
            } else if (courses.isEmpty()) {
                out.println("<p>Listan 'courses' är tom.</p>");
            } else {
                for (String course : courses) {
        %>
            <li><%= course %></li>
        <% 
                }
            }
        %>
    </ul>
<%@ include file="footer.jsp" %>
