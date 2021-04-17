package service;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableService implements Closeable {

    Connection connection;
    String tableName;

    public TableService(String tableName) throws SQLException {
        this.tableName = tableName;
        this.connection = DBExchange.getConnection();
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            System.out.println("Ошибка закрытия SQL соединения!");
        }
    }

    private void reopenConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DBExchange.getConnection();
        }
    }

    public void executeSqlStatement(String sql) throws SQLException {
        reopenConnection();
        Statement statement = connection.createStatement();
        statement.execute(sql);
        statement.close();
    };
}
