package service;

import dto.UsersQuestionary;
import interfaces.TableCreate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersQuestionaryCreator extends TableService implements TableCreate {

    public UsersQuestionaryCreator() throws SQLException {
        super("users_questionary");
    }

    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS users_questionary(" +
                "login varchar(15) NOT NULL," +
                "id_questionary integer NOT NULL," +
                "user_answers varchar(255) NOT NULL," +
                "PRIMARY KEY (login, id_questionary))");
    }

    public void createForeignKeys() throws SQLException {
        super.executeSqlStatement("ALTER TABLE users_questionary ADD FOREIGN KEY (login) REFERENCES users(login) ON DELETE CASCADE ON UPDATE CASCADE");
        super.executeSqlStatement("ALTER TABLE users_questionary ADD FOREIGN KEY (id_questionary) REFERENCES " +
                "questionary(id_questionary) ON DELETE CASCADE ON UPDATE CASCADE");
    }

    public void writeAnswers (UsersQuestionary usersQuestionary) throws SQLException {
        try (PreparedStatement ps = DBExchange.getConnection().prepareStatement("INSERT INTO users_questionary" +
                "(login, id_questionary, user_answers) VALUES (?, ?, ?)")){
            ps.setString(1, usersQuestionary.getLogin());
            ps.setInt(2, usersQuestionary.getId_questionary());
            ps.setString(3, usersQuestionary.getAnswers());
            ps.executeUpdate();
        }
    }

    public String getUsersAnswers (UsersQuestionary usersQuestionary) throws SQLException {
        try (ResultSet rs = DBExchange.getConnection().createStatement().executeQuery("SELECT user_answers from " +
                "users_questionary WHERE login = \'" + usersQuestionary.getLogin() + "\' AND id_questionary = " +
                Integer.toString(usersQuestionary.getId_questionary()))){
            rs.next();
            return rs.getString(1);
        }
    }

    public String getUsers () throws SQLException {
        try (ResultSet rs = DBExchange.getConnection().createStatement().executeQuery("SELECT login FROM " +
                "users_questionary")){
            String users = "";
            while (rs.next()) {
                users += rs.getString(1) + "@";
            }
            return users;
        }
    }
}
