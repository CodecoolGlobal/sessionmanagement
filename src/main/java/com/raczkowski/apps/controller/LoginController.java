package com.raczkowski.apps.controller;

import com.raczkowski.apps.exception.MailFormatException;
import com.raczkowski.apps.model.User;
import com.raczkowski.apps.model.UserSqlDao;
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
    private CredentialsValidator credentialsValidator;
    private UserManagementService userManagementService;

    @Override
    public void init() throws ServletException {
        super.init();
//        this.userManagementService = new UserManagementService(new UserInMemoryDao());
        this.userManagementService = new UserManagementService(new UserSqlDao());
        this.credentialsValidator = new CredentialsValidator();
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

        HttpSession session = request.getSession(true);
        try {
            credentialsValidator.validateEmail(email);
            Optional<User> maybeUser = userManagementService.login(email, password);

            if (maybeUser.isPresent()) {
                invalidateOldSession(request);
                session.setAttribute("username", maybeUser.get());
                response.sendRedirect(request.getContextPath() + "/welcome");
            } else {
                session.setAttribute("message", "Invalid email/password");
                response.sendRedirect(request.getContextPath() + "/login");
            }

        } catch (MailFormatException exception) {
            session.setAttribute("message", exception.getMessage());
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
