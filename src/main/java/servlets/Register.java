package servlets;

import dto.Users;
import service.DBExchange;
import service.UsersCreator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class Register extends HttpServlet {

    UsersCreator usersCreator;
    DBExchange dbExchange;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        byte role = -1;
        switch (req.getParameter("roleSelector")){
            case "ADMIN":
                role = 1;
                break;
            case "USER":
                role = 2;
                break;
        }
        Users users = new Users(req.getParameter("login"), req.getParameter("password"), role);
        try {
            if (dbExchange == null)
                dbExchange = new DBExchange();
            if (usersCreator == null)
                usersCreator = new UsersCreator();
            usersCreator.insertNew(users);
            resp.sendRedirect(req.getContextPath()+"/");
        }
        catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("ex", e.toString());
            getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
        }

    }
}
