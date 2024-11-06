<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ include file="header.jsp" %>
<body>
    <h2>Kurser för Elev: <%= request.getAttribute("studentUsername") %></h2>
    <ul>
        <% 
            List<String> courses = (List<String>) request.getAttribute("courses");
            if (courses != null && !courses.isEmpty()) {
                for (String course : courses) {
        %>
            <li><%= course %></li>
        <% 
                }
            } else {
        %>
            <p>Inga kurser hittades för denna elev.</p>
        <% } %>
    </ul>
<%@ include file="footer.jsp" %>
