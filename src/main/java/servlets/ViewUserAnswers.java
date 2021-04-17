package servlets;

import dto.UsersQuestionary;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ViewUserAnswers extends HttpServlet {

    DBExchange dbExchange;
    UsersQuestionaryCreator usersQuestionaryCreator;
    QuestionaryCreator questionaryCreator;
    AnswerCreator answerCreator;
    QuestionCreator questionCreator;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String answerFormulations = "";
        String questionFormulations = "";
        try {
            if (dbExchange == null)
                dbExchange = new DBExchange();
            if (usersQuestionaryCreator == null)
                usersQuestionaryCreator = new UsersQuestionaryCreator();
            if (questionaryCreator == null)
                questionaryCreator = new QuestionaryCreator();
            if (answerCreator == null)
                answerCreator = new AnswerCreator();
            if (questionCreator == null)
                questionCreator = new QuestionCreator();
            UsersQuestionary uq = new UsersQuestionary(req.getParameter("role"),
                    questionaryCreator.getQuestId(req.getParameter("quest")), null);
            String[] userAnswers = usersQuestionaryCreator.getUsersAnswers(uq).split("\\|");
            ArrayList<Integer> questionIds = new ArrayList<Integer>();
            ArrayList<String> answersFormulations = new ArrayList<String>();
            for (String answerId: userAnswers) {
                questionIds.add(answerCreator.getQuestionIdByAnswerId(Integer.parseInt(answerId)));
                answersFormulations.add(answerCreator.getAnswerFormulation(Integer.parseInt(answerId)));
            }
            int tempQId = questionIds.get(0);
            answerFormulations += answersFormulations.get(0);
            questionFormulations += questionCreator.getFormulationById(questionIds.get(0)) + "@";
            for (int i = 1; i < questionIds.size(); i++) {
                if (tempQId == questionIds.get(i)) {
                    answerFormulations += "@";
                }
                else {
                    tempQId = questionIds.get(i);
                    answerFormulations += "|";
                    questionFormulations += questionCreator.getFormulationById(questionIds.get(i)) + "@";
                }
                answerFormulations += answersFormulations.get(i);
            }
            req.setAttribute("questionFormulations", questionFormulations);
            req.setAttribute("answerFormulations", answerFormulations);
            getServletContext().getRequestDispatcher("/WEB-INF/viewUserAnswers.jsp").forward(req, resp);
        }
        catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("ex", "Пользователь не прошёл эту анкету.");
            getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
    }
}
