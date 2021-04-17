package servlets;

import dto.Users;
import service.DBExchange;
import service.UsersCreator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class LogIn extends HttpServlet {
    UsersCreator usersCreator;
    DBExchange dbExchange;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Users users = new Users(req.getParameter("login"), req.getParameter("password"), (byte)-1);
        try {
            if (dbExchange == null)
                dbExchange = new DBExchange();
            if (usersCreator == null)
                usersCreator = new UsersCreator();
            int role = usersCreator.findUser(users);
            if (role != -1) {
                if (role == 1)
                    resp.sendRedirect(req.getContextPath()+"/questionaries?role=admin");
                else resp.sendRedirect(req.getContextPath()+"/questionaries?role=" + req.getParameter("login"));
            }
            else resp.sendRedirect(req.getContextPath()+"/register");
        }
        catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("ex", e.toString());
            getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
        }
    }
}
