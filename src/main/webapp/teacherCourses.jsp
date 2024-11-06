<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ include file="header.jsp" %>
<body>
    <h2>Alla Kurser</h2>
    <ul>
        <% 
            List<String> courses = (List<String>) request.getAttribute("courses");
            if (courses != null && !courses.isEmpty()) {
                for (int i = 0; i < courses.size(); i++) {
                    String course = courses.get(i);
        %>
            <li>
                <%= course %> 
                <a href="courseDetails?courseId=<%= i + 1 %>">Visa detaljer</a>
            </li>
        <% 
                }
            } else {
        %>
            <p>Inga kurser tillgängliga.</p>
        <% } %>
    </ul>
<%@ include file="footer.jsp" %>
