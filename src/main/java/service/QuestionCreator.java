package service;

import interfaces.TableCreate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionCreator extends TableService implements TableCreate {

    public QuestionCreator() throws SQLException {
        super("question");
    }

    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS question(" +
                "id_question integer AUTO_INCREMENT PRIMARY KEY," +
                "formulation varchar(255) NOT NULL UNIQUE," +
                "num_of_answers integer NOT NULL)");
    }

    public void createForeignKeys() throws SQLException {

    }

    public String getQuestions(int quest) throws SQLException {
        try (ResultSet rs = DBExchange.getConnection().createStatement().executeQuery("SELECT formulation FROM " +
                "question where id_question IN(SELECT id_question from questionary_question WHERE id_questionary = " +
                String.valueOf(quest) + ")")){
            String questions = "";
            while (rs.next()){
                questions += rs.getString(1) + "@";
            }
            return questions;
        }
    }

    public int getQuestionId(String formulation) throws SQLException {
        try (ResultSet rs = DBExchange.getConnection().createStatement().executeQuery("SELECT id_question FROM" +
                " question WHERE formulation = \'" + formulation + "\'")) {
            rs.next();
            return rs.getInt(1);
        }
    }

    public void addQuestion(String formulation, int num_of_answers) throws SQLException {
        try (PreparedStatement ps = DBExchange.getConnection().prepareStatement("INSERT INTO question(formulation," +
                "num_of_answers) VALUES (?, ?)")){
            ps.setString(1, formulation);
            ps.setInt(2, num_of_answers);
            ps.executeUpdate();
        }
    }

    public void deleteQuestion(String formulation) throws SQLException {
        try (PreparedStatement ps = DBExchange.getConnection().prepareStatement("DELETE FROM question WHERE " +
                "formulation = ?")){
            ps.setString(1, formulation);
            ps.executeUpdate();
        }
    }

    public String getNumsOfAnswers(int quest) throws SQLException {
        try (ResultSet rs = DBExchange.getConnection().createStatement().executeQuery("SELECT num_of_answers FROM " +
                "question where id_question IN(SELECT id_question from questionary_question WHERE id_questionary = " +
                String.valueOf(quest) + ")")) {
            String nums = "";
            while (rs.next()){
                nums += rs.getInt(1) + "@";
            }
            return nums;
        }
    }

    public String getFormulationById(int id) throws SQLException {
        try (ResultSet rs = DBExchange.getConnection().createStatement().executeQuery("SELECT formulation FROM" +
                " question WHERE id_question = " + Integer.toString(id))) {
            rs.next();
            return rs.getString(1);
        }
    }
}
