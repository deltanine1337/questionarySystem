package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBExchange {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~\\questDB";
    static final String USER = "sa";
    static final String PASS = "";
    AnswerCreator answerCreator;
    QuestionCreator questionCreator;
    QuestionaryCreator questionaryCreator;
    QuestionaryQuestionCreator questionaryQuestionCreator;
    UsersCreator usersCreator;
    UsersQuestionaryCreator usersQuestionaryCreator;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public DBExchange() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        answerCreator = new AnswerCreator();
        questionaryCreator = new QuestionaryCreator();
        questionCreator = new QuestionCreator();
        questionaryQuestionCreator = new QuestionaryQuestionCreator();
        usersCreator = new UsersCreator();
        usersQuestionaryCreator = new UsersQuestionaryCreator();
    }

    public void createTablesAndForeignKeys() throws SQLException {
        usersCreator.createTable();
        questionaryCreator.createTable();
        questionCreator.createTable();
        usersQuestionaryCreator.createTable();
        usersQuestionaryCreator.createForeignKeys();
        questionaryQuestionCreator.createTable();
        questionaryQuestionCreator.createForeignKeys();
        answerCreator.createTable();
        answerCreator.createForeignKeys();
    }
}
