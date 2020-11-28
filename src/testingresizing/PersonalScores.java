/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingresizing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
/**
 *
 * @author Abdullah
 */
public class PersonalScores {
    
    //an array for returning the scores
    private static int[] intArrPersonalScores = new int[10];
    public static void main(String[] args) {

        try {

            personalScoresRetrieval("Username2");
        }

        catch (Exception exception) {
            
            //System.out.println(exception);
        }
    }
    
    public static void personalScoresRetrieval(String username) throws Exception {

            //Connection Fields
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/typer";
            String userName = "root";
            String password = "Password@123";

            //Creating Connection with MySQL Database
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, userName, password);

            //Query for Retrieving Personal Scores
            PreparedStatement scoresRetrievalStatement = connection.prepareStatement(String.format(
                    "SELECT %s FROM wpm WHERE %s!=0 ORDER BY ATTEMPT DESC LIMIT 10", username,username));
            
            ResultSet personalScores = scoresRetrievalStatement.executeQuery();
            
            int counter = 0;
            //Printing Results
            while (personalScores.next()) {
                //System.out.println(personalScores.getInt(username));
                
                intArrPersonalScores[counter] = personalScores.getInt(username);
                counter++;
            }
            
            //Closing Connection
            connection.close();
    }
    
    public static int[] returnArrPersonalScores() {
        return intArrPersonalScores;
    }
}
