package servlets;

import service.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

public class Questions extends HttpServlet {
    QuestionCreator questionCreator;
    DBExchange dbExchange;
    QuestionaryCreator questionaryCreator;
    QuestionaryQuestionCreator questionaryQuestionCreator;
    AnswerCreator answerCreator;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String questions = "";
        String quests = "";
        String answers = "";
        try {
            if (dbExchange == null)
                dbExchange = new DBExchange();
            if (questionCreator == null)
                questionCreator = new QuestionCreator();
            if (questionaryCreator == null)
                questionaryCreator = new QuestionaryCreator();
            if (answerCreator == null)
                answerCreator = new AnswerCreator();
            questions = questionCreator.getQuestions(questionaryCreator.getQuestId(req.getParameter("quest")));
            quests = questionaryCreator.getAllQuests();
            if (questions != "") {
                String[] questionsList = questions.split("@");
                for (String s : questionsList) {
                    answers += answerCreator.getAnswers(questionCreator.getQuestionId(s));
                    answers += "|";
                }
                try {
                    answers = answers.substring(0, answers.length() - 1);
                } catch (Exception e) {
                }
            }
        }
            catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("ex", e.toString());
            getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
        }
        req.setAttribute("quests", quests);
        req.setAttribute("questions", questions);
        req.setAttribute("answers", answers);
        getServletContext().getRequestDispatcher("/WEB-INF/questions.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            if (dbExchange == null)
                dbExchange = new DBExchange();
            if (questionaryQuestionCreator == null)
                questionaryQuestionCreator = new QuestionaryQuestionCreator();
            if (questionCreator == null)
                questionCreator = new QuestionCreator();
            if (req.getParameter("addQuestion") != null) {
                String numSelector = req.getParameter("numSelector");
                int num_of_answers = -1;
                if (numSelector.equals("Один"))
                    num_of_answers = 1;
                else num_of_answers = 2;
                questionCreator.addQuestion(req.getParameter("formulation"), num_of_answers);
                questionaryQuestionCreator.addQuestionIntoQuestionary(questionaryCreator.getQuestId(req.getParameter
                        ("quest")), questionCreator.getQuestionId(req.getParameter("formulation")));
                resp.sendRedirect(req.getContextPath()+"/questions?quest=" + URLEncoder.encode
                        (req.getParameter("quest"), "utf-8"));
            }
            else if (req.getParameter("addAnswer") != null){
                String[] answersList = req.getParameter("formulation").split(";");
                if (answersList.length < 2) {
                    req.setAttribute("ex", "Количество ответов меньше двух.");
                    getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
                }
                else {
                    int question = questionCreator.getQuestionId(req.getParameter("questionSelector"));
                    for (String s : answersList) {
                        answerCreator.addAnswer(question, s);
                    }
                    resp.sendRedirect(req.getContextPath() + "/questions?quest=" + URLEncoder.encode
                            (req.getParameter("quest"), "utf-8"));
                }
            }
            else if (req.getParameter("deleteButton") != null){
                String questionFormulation = req.getParameter("deleteButton");
                questionaryQuestionCreator.deleteQuestionFromQuestionary(questionaryCreator.getQuestId(req.getParameter
                        ("quest")), questionCreator.getQuestionId(questionFormulation));
                questionCreator.deleteQuestion(questionFormulation);
                resp.sendRedirect(req.getContextPath()+"/questions?quest=" + URLEncoder.encode
                        (req.getParameter("quest"), "utf-8"));
            }
            else if (req.getParameter("changeButton") != null){
                String questionFormulation = URLEncoder.encode(req.getParameter("changeButton"), "utf-8");
                resp.sendRedirect(req.getContextPath()+"/editAnswers?quest=" + URLEncoder.encode
                        (req.getParameter("quest"), "utf-8") +
                        "&question=" + questionFormulation);
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            req.setAttribute("ex", e.toString());
            getServletContext().getRequestDispatcher("/WEB-INF/ex.jsp").forward(req, resp);
        }
    }
}
