package main;

import db.connection.ConnectToDatabase;
import db.connection.EstablishConnection;
import db.request.InsertRequest;
import db.request.SelectRequest;
import db.request.UpdateRequest;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static SelectRequest selectRequest = new SelectRequest();
    private static InsertRequest insertRequest = new InsertRequest();
    private static UpdateRequest updateRequest = new UpdateRequest();
    private static Scanner scanner = new Scanner(System.in);
    private static final ConnectToDatabase connectToDatabase = EstablishConnection.getInstance();

    public static void main(String[] args) {


        System.out.println("Please, enter your number : if you have an account, enter 1," +
                "if you want to create an account, enter 2");

        int choice = scanner.nextInt();
        Main main = new Main();
        try {
            main.doActionBasedOnChoice(choice);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }

    private void doActionBasedOnChoice(int choice) throws IOException, NoSuchAlgorithmException {

        if (choice == 1) {
            System.out.println("Enter your username");
            String user = scanner.next();

            System.out.println("Enter your password");
            String password = scanner.next();
            //byte[] bytesOfPassword = password.getBytes();


            String userNameFromDB =
                    selectRequest.resultSet("user_details", "username", user);
            //System.out.println("user from DB " + userNameFromDB);
            String passwordFromDB =
                    selectRequest.resultSet("user_details", "password", password);
            //System.out.println("password from DB " + passwordFromDB);
            //String firstEnteredInput = new String(bytesOfPassword);
            //String secondFromDatabase = new String(passwordFromDB);
            if (user.equals(userNameFromDB) && passwordFromDB.equals(password)) {
                System.out.println("Welcome! You have an account in DB and you logged in");
                System.out.println("Do you want to change your password? Enter your choice : 1 for changing or 2 for exit");
                int choice1 = scanner.nextInt();
                if (choice1 == 1) {
                    Main.hasChangedPassword();
                } else {
                    System.exit(1);
                }
            } else {
                System.out.println("Please write correct user details");
            }
        } else if (choice == 2) {
            System.out.println("Enter your user details : \nenter your username");
            String userName = scanner.next();
            System.out.println("Enter your main email");
            String mainEmail = scanner.next();
            /*byte[] userByte = userName.getBytes();
            byte[] userEmail = mainEmail.getBytes();*/

            String emailAddress =
                    selectRequest.resultSet("contact_of_user", "email", mainEmail);
            String userNameFromDataBase =
                    selectRequest.resultSet("user_details", "username", userName);

            if (userName.equals(userNameFromDataBase) || mainEmail.equals(emailAddress)) {
                System.out.println("This is on our DB. Do you want to change your password? Enter your choice : 1 for yes, 2 for exit");

                int choice1 = scanner.nextInt();

                if (choice1 == 1) {
                    Main.hasChangedPassword();
                } else if (choice1 == 2) {
                    System.exit(1);
                }
            } else {

                System.out.println("Enter your name");
                String name = scanner.next();

                System.out.println("Enter your surname");
                String surname1 = scanner.next();

                System.out.println("Enter your password");
                String password = scanner.next();

                Date date = new Date(System.currentTimeMillis());

                insertRequest.isInsertedToUser(name, surname1, userName, password, date);

                System.out.println("Enter your second email");
                String email2 = scanner.next();
                System.out.println("Enter your number");
                String num = scanner.next();
                System.out.println("Enter your number");
                String num1 = scanner.next();
                System.out.println("Enter your number");
                String num2 = scanner.next();
                System.out.println("Enter your living place");
                String city = scanner.next();

                insertRequest.isInsertedToContact(num, mainEmail, city, email2, num1, num2);
            }
            System.out.println("User created");

        }
    }

    private static boolean hasChangedPassword() throws IOException {

        System.out.println("Enter your username");
        String username = scanner.next();
        System.out.println("Enter your main email");
        String mailEmail = scanner.next();
        System.out.println("Enter your new password");
        String newPassword = scanner.next();
        System.out.println("Enter your new password");
        String newPasswordAgain = scanner.next();
        /*byte[] userByte = username.getBytes();
        byte[] userEmail = mailEmail.getBytes();*/

        String selectedUserFromDB = selectRequest.resultSet("user_details", "username", username);
        String selectedMailFromBD = selectRequest.resultSet("contact_of_user", "email", mailEmail);


        if (selectedMailFromBD != null && selectedUserFromDB != null && newPassword.equals(newPasswordAgain)) {
            updateRequest.isUpdated(username, newPassword);
            System.out.println("Updated");
            return true;
        }
        return false;
    }

}
