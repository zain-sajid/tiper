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
public class TopScoresRetrieval {
    public static void main(String[] args) {

        try {

            getTopScore("Username2");
        }

        catch (Exception exception) {

            //System.out.println(exception);
        }
    }

    public static void getTopScore(String username) throws Exception {

            //Connection Fields
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/typer";
            String userName = "root";
            String password = "Password@123";

            //Creating Connection with MySQL Database
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, userName, password);

            //Statement for Retrieving Top Score
            PreparedStatement preparedStatement1 = connection.prepareStatement(String.format(
                "SELECT %s,ATTEMPT FROM wpm ORDER BY %s DESC LIMIT 1",username,username));

            //Executing Query
            ResultSet resultSet = preparedStatement1.executeQuery();
            resultSet.next();

            //Variables for Top Score and Attempt Number
            int topScore=resultSet.getInt(username);
            int attemptNumber = resultSet.getInt("ATTEMPT");
            
            //Querires to Store Top Score
             PreparedStatement preparedStatement2 = connection
                .prepareStatement(String.format("UPDATE user_profiles SET BEST_SCORE=%d WHERE USERNAME='%s'",topScore,username));

            preparedStatement2.executeUpdate();

            PreparedStatement preparedStatement3=connection
                .prepareStatement(String.format(
                        "SELECT %s FROM accuracy WHERE ATTEMPT=%d",username,attemptNumber));

            ResultSet resultSet2 = preparedStatement3.executeQuery();
            resultSet2.next();
            int accuracy = resultSet2.getInt(username);
            

            PreparedStatement preparedStatement4=connection
                .prepareStatement(String.format(
                            "UPDATE user_profiles SET ACCURACY=%d WHERE USERNAME='%s'", accuracy, username));
            
            preparedStatement4.executeUpdate();

            //Final Results
            //System.out.println(topScore);
            //System.out.println(accuracy);

            //Closing Connection
            connection.close();
    }
}
