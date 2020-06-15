package com.raczkowski.apps.controller;

import com.raczkowski.apps.model.User;
import com.raczkowski.apps.model.UserInMemoryDao;
import com.raczkowski.apps.service.UserManagementService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private UserManagementService userManagementService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userManagementService = new UserManagementService(new UserInMemoryDao());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/WEB-INF/views/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Optional<User> maybeUser = userManagementService.login(email, password);
        if (maybeUser.isPresent()) {
            invalidateOldSession(request);
            HttpSession session = request.getSession(true);
            session.setAttribute("username", maybeUser.get());
            response.sendRedirect(request.getContextPath() + "/welcome");
        } else {
            String message = "Invalid email/password";
            request.setAttribute("message", message);
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    private void invalidateOldSession(HttpServletRequest request) {
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }
    }
}
