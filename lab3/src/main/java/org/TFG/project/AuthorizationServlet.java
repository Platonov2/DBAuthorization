package org.TFG.project;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String dbPassword = null;
        String dbEmail = null;
        UserProfile userProfile = null;
        String href = "http://localhost:8000/?path=C:\\" + login;

        DBControler.OpenDataBase();
        ResultSet result = DBControler.ExecuteCommandAndReturn("SELECT * FROM USERS2 WHERE login = '" + login + "'");
        try {
            result.next();
            dbPassword = result.getString("password");
            dbEmail = result.getString("email");

            userProfile = new UserProfile(login, dbPassword, dbEmail);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (dbPassword == null || !dbPassword.equals(password)){
            request.getRequestDispatcher("/authorization.html").forward(request, response);
            return;
        }

        String sessionId = request.getSession().getId();
        AccountService.addSession(sessionId, userProfile);

        response.sendRedirect(href);
    }

    public void deDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String sessionId = request.getSession().getId();
        AccountService.deleteSession(sessionId);
    }
}
