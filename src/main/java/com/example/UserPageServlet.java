package com.example;


import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && "confirmed".equals(session.getAttribute("stateType"))) {
            request.getRequestDispatcher("userPage.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
