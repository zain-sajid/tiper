/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingresizing;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Abdullah
 */
public class StartTestResultController implements Initializable {

    @FXML
    private Label wpm_result_label;
    @FXML
    private Label accuracy_result_label;
    @FXML
    private Label speed_result_label;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        wpm_result_label.setText(String.valueOf(StartTestPageController.wpmDatabase));
        accuracy_result_label.setText(String.valueOf(StartTestPageController.accuracyDatabase));
    }    
    
}
