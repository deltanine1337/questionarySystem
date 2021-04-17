package servlets;

import service.DBExchange;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateDB extends HttpServlet {
    DBExchange dbExchange;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            if (dbExchange == null)
                dbExchange = new DBExchange();
            dbExchange.createTablesAndForeignKeys();
            getServletContext().getRequestDispatcher("/WEB-INF/createDB.jsp").forward(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("ex", e.toString());
            getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
