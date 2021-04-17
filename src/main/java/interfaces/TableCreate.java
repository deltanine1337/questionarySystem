package interfaces;

import java.sql.SQLException;

public interface TableCreate {
    void createTable() throws SQLException;
    void createForeignKeys() throws SQLException;
}
