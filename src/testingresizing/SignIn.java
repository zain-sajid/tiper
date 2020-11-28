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
public class SignIn {
    public static void main(String[] args) {

        try{
            
            signIn("Username", "123");
        }

        catch (Exception exception) {
            
            //System.out.println(exception);
        }
    }
    
    public static boolean signIn(String username, String userPassword) throws Exception{

        //Connection Fields
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/typer";
        String userName = "root";
        String password = "Password@123";

        //Creating Connection with MySQL Database
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, userName, password);

        PreparedStatement usersRetrievalStatement=connection
                .prepareStatement("SELECT USERNAME,PASSWORD FROM user_profiles");
            
        ResultSet users = usersRetrievalStatement.executeQuery();

        boolean check = false;

        while (users.next()) {

            if (users.getString("USERNAME").equals(username) && users.getString("PASSWORD").equals(userPassword)){

                check = true;
            }
        }
        
        if (check == true) {

            //System.out.println("Welcome!");
        }

        else {

            //System.out.println("Incorrect Credentials!");
        }
        
        return check;
    }
}
