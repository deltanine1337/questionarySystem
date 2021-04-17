package service;

import interfaces.TableCreate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionaryCreator extends TableService implements TableCreate {
    public QuestionaryCreator() throws SQLException {
        super("questionary");
    }

    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS questionary(" +
                "id_questionary integer AUTO_INCREMENT PRIMARY KEY," +
                "name_questionary varchar(50) NOT NULL UNIQUE)");
    }

    public void createForeignKeys() throws SQLException {

    }

    public String getAllQuests() throws SQLException {
        try (ResultSet rs = DBExchange.getConnection().createStatement().executeQuery("SELECT * FROM questionary")){
            String result = "";
            while (rs.next()) {
                result += rs.getString(2) + ",";
            }
            return result;
        }
    }

    public void addQuest(String name) throws SQLException {
            try (PreparedStatement ps = DBExchange.getConnection().prepareStatement("INSERT INTO questionary" +
                    "(name_questionary) VALUES (?)")){
                ps.setString(1, name);
                ps.executeUpdate();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
    }

    public int getQuestId(String name) throws SQLException {
        try (ResultSet rs = DBExchange.getConnection().createStatement().executeQuery("SELECT id_questionary FROM" +
                " questionary WHERE name_questionary = \'" + name + "\'")){
            rs.next();
            return rs.getInt(1);
        }
    }
}
