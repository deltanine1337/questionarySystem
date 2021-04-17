package service;

import interfaces.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerCreator extends TableService implements TableCreate{

    public AnswerCreator() throws SQLException {
        super("answer");
    }

    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS answer(" +
                "id_question integer NOT NULL," +
                "id_answer integer AUTO_INCREMENT," +
                "formulation varchar(255) NOT NULL," +
                "PRIMARY KEY (id_question, id_answer))");
    }

    public void createForeignKeys() throws SQLException {
        super.executeSqlStatement("ALTER TABLE answer ADD FOREIGN KEY (id_question) REFERENCES question(id_question) ON DELETE CASCADE ON UPDATE CASCADE");
    }

    public String getAnswers(int question) throws SQLException {
        try (ResultSet rs = DBExchange.getConnection().createStatement().executeQuery("SELECT formulation FROM " +
                "answer where id_question = " + String.valueOf(question))){
            String answers = "";
            while (rs.next()){
                answers += rs.getString(1) + "@";
            }
            try {
                answers = answers.substring(0, answers.length() -1);
            }
            catch (Exception e) {}
            return answers;
        }
    }

    public void addAnswer (int question, String formulation) throws SQLException {
        try (PreparedStatement ps = DBExchange.getConnection().prepareStatement("INSERT INTO answer(id_question," +
                "formulation) VALUES (?, ?)")){
            ps.setInt(1, question);
            ps.setString(2, formulation);
            ps.executeUpdate();
        }
    }

    public void deleteAnswersInQuestion(int question) throws SQLException {
        try (PreparedStatement ps = DBExchange.getConnection().prepareStatement("DELETE FROM answer WHERE " +
                "id_question = ?")){
            ps.setInt(1, question);
            ps.executeUpdate();
        }
    }

    public int getAnswerId(String formulation, int idQuestion) throws SQLException {
        try (ResultSet rs = DBExchange.getConnection().createStatement().executeQuery("SELECT id_answer FROM" +
                " answer WHERE formulation = \'" + formulation + "\' AND id_question = " + Integer.toString(idQuestion))) {
            rs.next();
            return rs.getInt(1);
        }
    }

    public int getQuestionIdByAnswerId(int id) throws SQLException {
        try (ResultSet rs = DBExchange.getConnection().createStatement().executeQuery("SELECT id_question FROM" +
                " answer WHERE id_answer = " + Integer.toString(id))) {
            rs.next();
            return rs.getInt(1);
        }
    }

    public String getAnswerFormulation (int idAnswer) throws SQLException {
        try (ResultSet rs = DBExchange.getConnection().createStatement().executeQuery("SELECT formulation FROM" +
                " answer WHERE id_answer = " + Integer.toString(idAnswer))) {
            rs.next();
            return rs.getString(1);
        }
    }
}
