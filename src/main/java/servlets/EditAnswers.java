package servlets;

import service.AnswerCreator;
import service.DBExchange;
import service.QuestionCreator;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

public class EditAnswers extends HttpServlet {
    DBExchange dbExchange;
    AnswerCreator answerCreator;
    QuestionCreator questionCreator;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String answers = "";
        try {
            if (dbExchange == null)
                dbExchange = new DBExchange();
            if (answerCreator == null)
                answerCreator = new AnswerCreator();
            if (questionCreator == null)
                questionCreator = new QuestionCreator();
            answers = answerCreator.getAnswers(questionCreator.getQuestionId(req.getParameter("question")));
            req.setAttribute("answers", answers);
            getServletContext().getRequestDispatcher("/WEB-INF/editAnswers.jsp").forward(req, resp);
        }
        catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("ex", e.toString());
            getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            if (dbExchange == null)
                dbExchange = new DBExchange();
            if (answerCreator == null)
                answerCreator = new AnswerCreator();
            if (questionCreator == null)
                questionCreator = new QuestionCreator();
            answerCreator.deleteAnswersInQuestion(questionCreator.getQuestionId(req.getParameter("question")));
            String[] answersList = req.getParameter("newAnswers").split(";");
            if (answersList.length < 2) {
                req.setAttribute("ex", "Количество ответов меньше двух.");
                getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
            }
            else {
                int question = questionCreator.getQuestionId(req.getParameter("question"));
                for (String s : answersList) {
                    answerCreator.addAnswer(question, s);
                }
                resp.sendRedirect(req.getContextPath() + "/questions?quest=" + URLEncoder.encode
                        (req.getParameter("quest"), "utf-8"));
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("ex", e.toString());
            getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
        }

    }

}
