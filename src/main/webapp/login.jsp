<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Grit Academy Portal</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    <h2>Logga in</h2>
    <form action="login" method="post">
        <label for="username">Användarnamn:</label>
        <input type="text" name="username" id="username" required>
        <br>
        <label for="password">Lösenord:</label>
        <input type="password" name="password" id="password" required>
        <br>
        <button type="submit">Logga in</button>
    </form>
    <% if (request.getParameter("error") != null) { %>
        <p style="color: red;">Ogiltigt användarnamn eller lösenord. Försök igen.</p>
    <% } %>
    <%@ include file="footer.jsp" %>
</body>
</html>
