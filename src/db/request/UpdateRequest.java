package db.request;

import db.connection.EstablishConnection;
import db.password.encoding.Encode;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateRequest {

    private EstablishConnection connection;

    public UpdateRequest() {
        this.connection = EstablishConnection.getInstance();
    }


    public boolean isUpdated(String username, String newPassword) throws IOException {
        boolean updated = false;
        String query = "UPDATE user_details SET password = ? WHERE username = ? ";
        try {
            Connection connection = this.connection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, Encode.encodePassword(newPassword));
            statement.setString(2, username);
            statement.executeUpdate();
            updated = true;
        } catch (NoSuchAlgorithmException | SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

}
