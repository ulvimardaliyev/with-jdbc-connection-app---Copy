package db.request;

import db.connection.EstablishConnection;
import db.password.encoding.Encode;

import java.sql.*;

public class SelectRequest {
    private EstablishConnection connection;


    public SelectRequest() {
        this.connection = EstablishConnection.getInstance();
    }

    public String resultSet(String tableName, String columnName, String userDetails1) {

        String result = "";
        String query = "SELECT " + columnName + " FROM " + tableName + " WHERE " + columnName + " = ? ";


        try (PreparedStatement statement = this.connection.getConnection().prepareStatement(query)) {
            String keepMe = userDetails1;
            if (columnName.equals("password")) {
                userDetails1 = Encode.encodePassword(userDetails1);
            }
            statement.setString(1, userDetails1);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //System.out.println("inside resultSet loop");
                if (resultSet.getString(columnName).equals(userDetails1)) {
                    result = keepMe;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("result is " + result);
        return result;
    }

    public String resultSet(String tableName, String columnName) {

        ResultSet resultSet;
        String resultFromTable = null;

        try (Statement statement = this.connection.getConnection().createStatement()) {
            resultSet =
                    statement.executeQuery("Select " + columnName + " FROM " + tableName);
            while (resultSet.next()) {
                resultFromTable = resultSet.getString(columnName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultFromTable;
    }
}
