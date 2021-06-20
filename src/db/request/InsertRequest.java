package db.request;

import db.connection.EstablishConnection;
import db.password.encoding.Encode;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class InsertRequest {

    private EstablishConnection connection;
    private SelectRequest selectRequest;


    public InsertRequest() {
        this.connection = EstablishConnection.getInstance();
        this.selectRequest = new SelectRequest();
    }

    public boolean isInsertedToUser(String name, String surname, String username,
                                    String password, Date registrationDate) throws NoSuchAlgorithmException {
        boolean inserted = false;
        String userQuery = "INSERT into user_details (name, surname, username, password, registrationDate)" +
                " values (?,?,?,?,?) ";
        //password = Encode.encodePassword(password);
        try (PreparedStatement ps =
                     this.connection.getConnection().prepareStatement(userQuery)) {
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setString(3, username);
            ps.setString(4, Encode.encodePassword(password));
            ps.setDate(5, registrationDate);
            ps.execute();
            inserted = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inserted;
    }

    public boolean isInsertedToContact(String num, String mainEmail,
                                       String city, String email2, String num1, String num2) {
        boolean inserted = false;
        String contactQuery = "INSERT into contact_of_user (user_phone_number, email, city, " +
                "userId, additional_email, additional_user_phone_number)" +
                " values (?,?,?,?,?,?) ";
        String resultOfID = selectRequest.resultSet("user_details", "userID");


        //System.out.println("Result of ID " + resultOfID);
        try (PreparedStatement contactPS = this.connection.getConnection().prepareStatement(contactQuery)) {
            contactPS.setString(1, num);
            contactPS.setString(2, mainEmail);
            contactPS.setString(3, city);
            contactPS.setInt(4, Integer.parseInt(resultOfID));
            contactPS.setString(5, email2);
            contactPS.setString(6, num1);
            contactPS.addBatch();
            contactPS.executeBatch();
            contactPS.setString(1, num);
            contactPS.setString(2, mainEmail);
            contactPS.setString(3, city);
            contactPS.setInt(4, Integer.parseInt(resultOfID));
            contactPS.setString(5, email2);
            contactPS.setString(6, num2);
            contactPS.addBatch();
            contactPS.executeBatch();
            inserted = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inserted;
    }
}