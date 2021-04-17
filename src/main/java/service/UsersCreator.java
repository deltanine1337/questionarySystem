package service;

import interfaces.TableCreate;
import dto.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersCreator extends TableService implements TableCreate {

    public UsersCreator() throws SQLException {
        super("users");
    }

    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS users(" +
                "login varchar(15) PRIMARY KEY," +
                "password varchar(32) NOT NULL," +
                "role tinyint NOT NULL)");
    }

    public void createForeignKeys() throws SQLException{

    }

    public void insertNew(Users u) throws SQLException {
        try(PreparedStatement ps = DBExchange.getConnection().prepareStatement("INSERT INTO users VALUES (?,?,?)")){
            ps.setString(1,u.getLogin());
            ps.setString(2, u.getPassword());
            ps.setInt(3, u.getRole());
            ps.executeUpdate();
        }
    }

    public int findUser(Users u) throws SQLException {
        try(PreparedStatement ps = DBExchange.getConnection().prepareStatement("SELECT * FROM users where " +
                "login = ? and password = ?")){
            ps.setString(1,u.getLogin());
            ps.setString(2, u.getPassword());
            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst()){
                return -1;
            }
            else {
                rs.next();
                return rs.getInt(3);
            }
        }
    }
}
