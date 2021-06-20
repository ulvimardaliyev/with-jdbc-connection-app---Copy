package db.connection;

import java.sql.*;

public class EstablishConnection implements ConnectToDatabase {

    private static ParseDBConnectionDetails parseDBConnectionDetails;
    private static EstablishConnection establishConnection;
    private Connection connection;

    private EstablishConnection() {
        parseDBConnectionDetails = new ParseDBConnectionDetails();
        parseDBConnectionDetails.buildSQLConneectionProperty();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(parseDBConnectionDetails.getUrl(), parseDBConnectionDetails.getUser(), parseDBConnectionDetails.getPassword());
            System.out.println("Connected");
        } catch (SQLException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }


    public final static EstablishConnection getInstance() {
        if (establishConnection == null) {
            establishConnection = new EstablishConnection();
        }
        return establishConnection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
