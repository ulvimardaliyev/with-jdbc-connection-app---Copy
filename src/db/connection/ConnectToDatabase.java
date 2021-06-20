package db.connection;

import java.sql.Connection;

public interface ConnectToDatabase {
    Connection getConnection();
}
