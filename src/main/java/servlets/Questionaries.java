package servlets;

import service.DBExchange;
import service.QuestionaryCreator;
import service.UsersQuestionaryCreator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

public class Questionaries extends HttpServlet {

    QuestionaryCreator questionaryCreator;
    DBExchange dbExchange;
    UsersQuestionaryCreator usersQuestionaryCreator;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String quests = "";
        String users = "";
        try {
            if (dbExchange == null)
                dbExchange = new DBExchange();
            if (questionaryCreator == null)
            questionaryCreator = new QuestionaryCreator();
            if (usersQuestionaryCreator == null)
                usersQuestionaryCreator = new UsersQuestionaryCreator();
            quests = questionaryCreator.getAllQuests();
            users = usersQuestionaryCreator.getUsers();
            req.setAttribute("quests", quests);
            req.setAttribute("users", users);
            getServletContext().getRequestDispatcher("/WEB-INF/questionaries.jsp").forward(req, resp);
        } catch (ClassNotFoundException | SQLException e) {
            req.setAttribute("ex", e.toString());
            getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
        }


    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        if (req.getParameter("userButton") != null) {
            resp.sendRedirect(req.getContextPath()+"/viewUserAnswers?quest=" + URLEncoder.encode
                    (req.getParameter("queSelector"), "utf-8") +
                    "&role=" + URLEncoder.encode(req.getParameter("userSelector"), "utf-8"));
        }
        else {
            try {
                if (dbExchange == null)
                    dbExchange = new DBExchange();
                if (questionaryCreator == null)
                    questionaryCreator = new QuestionaryCreator();
                questionaryCreator.addQuest(req.getParameter("questName"));
                resp.sendRedirect(req.getContextPath() + "/questionaries?role=" + URLEncoder.encode
                        (req.getParameter("role"), "utf-8"));
            } catch (ClassNotFoundException | SQLException e) {
                req.setAttribute("ex", e.toString());
                getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
            }
        }
    }
}
