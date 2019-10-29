package org.TFG.project;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        UserProfile user = new UserProfile(login, password, email);

        DBControler.OpenDataBase();
        DBControler.CreateTable();
        DBControler.ExecuteCommand("INSERT INTO USERS2 (login, password, email) VALUES ('" + login + "', '" + password + "', '" + email + "')");


        request.getRequestDispatcher("/authorization.html").forward(request, response);
    }
}
