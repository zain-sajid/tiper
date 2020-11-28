/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingresizing;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abdullah
 */
public class SettingsPageController implements Initializable {

    @FXML
    private Button main_page_btn;
    @FXML
    private ComboBox<String> time_combo_box;
    @FXML
    private Slider goal_slider;
    @FXML
    private Label goal_slider_label;
    //the combobox value
    private static String timetime;
    //the time for test
    public static int sendTime = 60;
    //the goal
    public static double goalgoal = 40;

    //list of time for combo box
    ObservableList<String> timeOptions
            = FXCollections.observableArrayList(
                    "30 seconds",
                    "1 minute",
                    "2 minute",
                    "5 minute"
            );

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //setting the items
        time_combo_box.setItems(timeOptions);
        //setting the preset to 1 minute
        time_combo_box.setValue("1 minute");
        
        //setting the slider label to the value
        goal_slider_label.setText(String.format("%.0f", goal_slider.getValue()));
        
        //adding the listener for the slider
        goal_slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    goal_slider_label.setText(String.format("%.0f", new_val));
            }
        });
    }

    @FXML
    private void gotoMainPage(ActionEvent event) {
        goalgoal = goal_slider.getValue();
        timetime = time_combo_box.getValue();
        if (timetime.equals("30 seconds")) {
            sendTime = 30;
        } else if (timetime.equals("2 minute")) {
            sendTime = 120;
        } else if (timetime.equals("5 minute")) {
            sendTime = 300;
        }
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
            Node source = (Node) event.getSource();
            Scene scene = source.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
