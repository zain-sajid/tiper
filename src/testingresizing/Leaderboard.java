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
public class Leaderboard {
    
    //testing
    //static testLeader[] arr = new testLeader[10];
    //static String[] stringArr = new String[10];
    private static String[][] stringArrLeaderboard = new String[10][3];
    
    
    public static void main(String[] args) {

        try{

            leaderboards();
        }

        catch (Exception exception) {
            
            //System.out.println(exception);
        }
    }
    
    public static void leaderboards() throws Exception {

            //Connection Fields
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/typer";
            String userName = "root";
            String password = "Password@123";

            //Creating Connection with MySQL Database
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, userName, password);

            //Query for Retrieving Leaderboards
            PreparedStatement leaderboardsRetrievalStatement = connection.prepareStatement(
                    "SELECT USERNAME,BEST_SCORE,ACCURACY FROM user_profiles ORDER BY BEST_SCORE DESC LIMIT 10");
            
            ResultSet leaderboards = leaderboardsRetrievalStatement.executeQuery();
            
            //an array of type testLeader class
            int counterblah = 0;
            //int innercounter = 0;
//            while (leaderboards.next()) {
//
//            }
            
            
            //Printing Leaderboards
            while (leaderboards.next()) {
                //"1", "Zain Sajid", "80", "99.99"
                //stringArr[counterblah] = String.format("\"%d\", \"%s\", \"%s\", \"%s\"", counterblah+1, leaderboards.getString("USERNAME"), Integer.toString(leaderboards.getInt("BEST_SCORE")), Integer.toString(leaderboards.getInt("ACCURACY")));
                
                stringArrLeaderboard[counterblah][0] = leaderboards.getString("USERNAME");
                stringArrLeaderboard[counterblah][1] = Integer.toString(leaderboards.getInt("BEST_SCORE"));
                stringArrLeaderboard[counterblah][2] = Integer.toString(leaderboards.getInt("ACCURACY"));
                
//                arr[counterblah] = new testLeader(leaderboards.getString("USERNAME"), Integer.toString(leaderboards.getInt("BEST_SCORE")), Integer.toString(leaderboards.getInt("ACCURACY")));
//                System.out.println(leaderboards.getString("USERNAME") + "\t" + leaderboards.getInt("BEST_SCORE") + "\t"
//                        + leaderboards.getInt("ACCURACY"));
//                
                counterblah++;
            }
            
            //Closing Connection
            connection.close();
            
    }

    public static String[][] returnArrLeaderboard() {
        return stringArrLeaderboard;
    }
    
//    public static class testLeader {
//        String username1;
//        String score1;
//        String accuracy1;
//
//        public testLeader(String username1, String score1, String accuracy1) {
//            this.username1 = username1;
//            this.score1 = score1;
//            this.accuracy1 = accuracy1;
//        }
//        
//    }
}
