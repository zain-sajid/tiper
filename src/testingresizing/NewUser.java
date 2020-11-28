/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingresizing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Abdullah
 */
public class NewUser {
    public static void main(String[] args) {

        try {

            addNewUser("User","Username2", "123","abd.com");
        } catch (Exception exception) {
            //System.out.println(exception);
        }
    }
    
    public static boolean addNewUser(String name, String username, String userPassword, String email) throws Exception {
        
        //Connection Fields
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/typer";
        String userName = "root";
        String password = "Password@123";

        //Creating Connection with MySQL Database
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, userName, password);

        PreparedStatement usernamesRetrievalStatement=connection
                .prepareStatement("SELECT USERNAME FROM user_profiles");
            
        ResultSet users = usernamesRetrievalStatement.executeQuery();

        boolean check = true;

        while (users.next()) {
            
            if (users.getString("USERNAME").equals(username)){

                check = false;
            }
        }
        
        if (check == false) {

            //System.out.println("Username already exists!");
        }

        else {

            //Creating & Executing MySQL Statements
            PreparedStatement newUserStatement = connection.prepareStatement(
                    String.format("INSERT INTO user_profiles(NAME,USERNAME,PASSWORD,EMAIL) VALUES('%s','%s','%s','%s')",
                            name, username, userPassword, email));

            //Executing Statement
            newUserStatement.executeUpdate();

            PreparedStatement newWpmUserStatement = connection
                    .prepareStatement(String.format("ALTER TABLE wpm ADD COLUMN %s INT(3)", username));

            newWpmUserStatement.executeUpdate();

            PreparedStatement newAccuracyUserStatement = connection
                    .prepareStatement(String.format("ALTER TABLE accuracy ADD COLUMN %s INT(3)", username));

            newAccuracyUserStatement.executeUpdate();
        }
        
        //Closing Connection
        connection.close();
        
        return check;
    }
}
