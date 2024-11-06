<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<body>
    <h2>Välkommen till din sida</h2>
    <p>Du är inloggad som <%= session.getAttribute("userType") %>.</p>
    <nav>|
        <% if ("student".equals(session.getAttribute("userType"))) { %>
            <a href="studentCourses">Dina kurser</a> |
        <% } %>
        
        <a href="logout">Logga ut</a>
        <% if ("teacher".equals(session.getAttribute("userType"))) { %>
            <a href="teacherCourses">Se alla kurser</a> |
        <% } %>
        <% if ("teacher".equals(session.getAttribute("userType"))) { %>
            <a href="allStudents">Se alla elever</a> |
        <% } %>
        
        
        
    </nav>
<%@ include file="footer.jsp" %>
