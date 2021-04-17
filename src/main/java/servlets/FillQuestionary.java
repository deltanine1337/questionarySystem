package servlets;

import dto.UsersQuestionary;
import service.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

public class FillQuestionary extends HttpServlet {
    DBExchange dbExchange;
    QuestionCreator questionCreator;
    QuestionaryCreator questionaryCreator;
    AnswerCreator answerCreator;
    UsersQuestionaryCreator usersQuestionaryCreator;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String questions = "";
        String answers = "";
        String nums_of_answers = "";
        try {
            if (dbExchange == null)
                dbExchange = new DBExchange();
            if (questionCreator == null)
                questionCreator = new QuestionCreator();
            if (answerCreator == null)
                answerCreator = new AnswerCreator();
            if (questionaryCreator == null)
                questionaryCreator = new QuestionaryCreator();
            questions = questionCreator.getQuestions(questionaryCreator.getQuestId(req.getParameter("quest")));
            nums_of_answers = questionCreator.getNumsOfAnswers(questionaryCreator.getQuestId(req.getParameter
                    ("quest")));
            String[] questionsList = questions.split("@");
            for (String s : questionsList) {
                answers += answerCreator.getAnswers(questionCreator.getQuestionId(s));
                answers += "|";
            }
            try {
                answers = answers.substring(0, answers.length() - 1);
            } catch (Exception e) {}
        }
        catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("ex", e.toString());
            getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
        }
        req.setAttribute("questions", questions);
        req.setAttribute("answers", answers);
        req.setAttribute("nums_of_answers", nums_of_answers);
        getServletContext().getRequestDispatcher("/WEB-INF/fillQuestionary.jsp").forward(req, resp);
    }

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
            if (usersQuestionaryCreator == null)
                usersQuestionaryCreator = new UsersQuestionaryCreator();
            String[] userAnswers = req.getParameter("userAnswers").split("@");
            String answersForDB = "";
            for (String pairAQ: userAnswers) {
                String answer = pairAQ.split("&")[0];
                String question = pairAQ.split("&")[1];
                int qId = questionCreator.getQuestionId(question);
                answersForDB += answerCreator.getAnswerId(answer, qId) + "|";
            }
            UsersQuestionary uq = new UsersQuestionary(req.getParameter("role"),
                    questionaryCreator.getQuestId(req.getParameter("quest")), answersForDB);
            usersQuestionaryCreator.writeAnswers(uq);
            resp.sendRedirect(req.getContextPath()+"/questionaries?role=" + URLEncoder.encode
                    (req.getParameter("role"), "utf-8"));
        }
        catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("ex", e.toString());
            getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
        }
    }
}
