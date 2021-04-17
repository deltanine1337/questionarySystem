package service;

import interfaces.TableCreate;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionaryQuestionCreator extends TableService implements TableCreate {

    public QuestionaryQuestionCreator() throws SQLException {
        super("questionary_question");
    }

    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS questionary_question(" +
                "id_questionary integer," +
                "id_question integer," +
                "PRIMARY KEY (id_questionary, id_question))");
    }

    public void createForeignKeys() throws SQLException{
        super.executeSqlStatement("ALTER TABLE questionary_question ADD FOREIGN KEY (id_questionary) REFERENCES" +
                " questionary(id_questionary) ON DELETE CASCADE ON UPDATE CASCADE");
        super.executeSqlStatement("ALTER TABLE questionary_question ADD FOREIGN KEY (id_question) REFERENCES" +
                " question(id_question) ON DELETE CASCADE ON UPDATE CASCADE");
    }

    public void addQuestionIntoQuestionary(int questionary, int question) throws SQLException {
        try (PreparedStatement ps = DBExchange.getConnection().prepareStatement("INSERT INTO questionary_question " +
                "VALUES (?,?)")){
            ps.setInt(1, questionary);
            ps.setInt(2, question);
            ps.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteQuestionFromQuestionary(int questionary, int question) throws SQLException {
        try (PreparedStatement ps = DBExchange.getConnection().prepareStatement("DELETE FROM questionary_question " +
                "WHERE id_questionary = ? AND id_question = ?")){
            ps.setInt(1, questionary);
            ps.setInt(2, question);
            ps.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
