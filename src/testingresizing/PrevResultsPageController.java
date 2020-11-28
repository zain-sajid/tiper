/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingresizing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Abdullah
 */
public class PrevResultsPageController implements Initializable {

    @FXML
    private Button main_page_btn;

    final static String date1 = "Attempt";
    final static String date2 = "Attempt";
    final static String date3 = "Attempt";
    final static String date4 = "Attempt";
    final static String date5 = "Attempt";
    final static String date6 = "Attempt";
    final static String date7 = "Attempt";
    final static String date8 = "Attempt";
    final static String date9 = "Attempt";
    final static String date10 = "Attempt";
    
    @FXML
    private BarChart<String, Number> results_bar_chart;
    @FXML
    private Label more_info_label;
    @FXML
    private Label label_label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //System.out.println("this is previous results page controller speaking");

        label_label.setText("You can select a certain bar\nto show further info");

        //calling the database function
        try {

            //extracting the user creds from the txt file
            String userLoggedIn = null;
            try {
                File myObj = new File("loginUserCredential.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    userLoggedIn = myReader.nextLine();
                    //System.out.println(userLoggedIn);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                //System.out.println("An error occurred.");
                //e.printStackTrace();
            }
            
            PersonalScores.personalScoresRetrieval(userLoggedIn);
        } catch (Exception ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int[] array = PersonalScores.returnArrPersonalScores();

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Date1");

        //setting up a temporary counter for the times
        // iterating through array 
        int counterAttempt = 1;
        for (int counter=array.length-1; counter >= 0;counter--) {
            //adding each element to the chart as a bar
            //System.out.println("i was here");
            String display = "Attempt " + counterAttempt;
            series1.getData().add(new XYChart.Data(display, array[counter]));
            counterAttempt++;
        }
        results_bar_chart.getData().addAll(series1);

        for (Series<String, Number> serie : results_bar_chart.getData()) {
            for (XYChart.Data<String, Number> item : serie.getData()) {
                item.getNode().setOnMousePressed((MouseEvent event) -> {
                    //System.out.println(item.getYValue());
                    //System.out.println("you clicked " + item.toString() + serie.toString());
                    more_info_label.setText("You scored a WPM \nof " + item.getYValue() + " in this test");
                });
            }
        }
    }

    @FXML
    void gotoMainPage(ActionEvent event) {
        //System.out.println("Going to the Main Page");
        //PrevResultsPageController mainPageController = new PrevResultsPageController();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
            //mainPageController.getRoot().setCenter(root);
            Node source = (Node) event.getSource();
            Scene scene = source.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
