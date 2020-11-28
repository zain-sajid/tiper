/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingresizing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
/**
 *
 * @author Abdullah
 */
public class ScoreStorage {
    public static void main(String[] args) {

        try {

        }

        catch (Exception exception) {

            //System.out.println(exception);
        }
    }
    
    public static void savingScore(String username, int score, int accuracy) throws Exception {

            //Connection Fields
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/typer";
            String userName = "root";
            String password = "Password@123";

            //Creating Connection with MySQL Database
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, userName, password);

            //Queries for Storing Scores
            PreparedStatement scoreSavingStatement = connection.prepareStatement(String.format(
                    "INSERT INTO wpm(%s) VALUES(%d)", username, score));

            scoreSavingStatement.executeUpdate();

            PreparedStatement accuracySavingStatement = connection.prepareStatement(String.format(
                    "INSERT INTO accuracy(%s) VALUES(%d)", username, accuracy));
                
            accuracySavingStatement.executeUpdate();

            //Closing Connection
            connection.close();
    }
}
