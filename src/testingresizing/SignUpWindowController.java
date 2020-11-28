/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingresizing;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abdullah
 */
public class SignUpWindowController implements Initializable {

    @FXML
    private Button close_sign_up_btn;
    @FXML
    private TextField text_field_username;
    @FXML
    private TextField text_field_email;
    @FXML
    private TextField text_field_password;
    @FXML
    private Button sign_up_btn;
    @FXML
    private CheckBox privacy_check_box;
    @FXML
    private TextField text_field_confirm_password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void closeSignUpWindow(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void signUpFucntion(ActionEvent event) {
        String username = text_field_username.getText();
        String email = text_field_email.getText();
        String password = text_field_password.getText();
        String passwordCheck = text_field_confirm_password.getText();

        //creating a drop shadow to be shown behind the invalid inputs
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.RED);

        //checking for checkbox first
        if (privacy_check_box.isSelected()) {
            //removing effect firstly
            privacy_check_box.setEffect(null);
            //System.out.println("you have checked the mark");

            //System.out.println("the username is valid : " + IsValidUsername(text_field_username.getText()));
            //checking for username validity
            if (IsValidUsername(text_field_username.getText())) {
                //removing effect firstly
                text_field_username.setEffect(null);

                //checking for password validity
                if (IsValidPassword(text_field_password.getText())) {
                    //removing effect firstly
                    text_field_password.setEffect(null);

                    //checking if passwords match
                    if (isSamePassword(password, passwordCheck)) {
                        //removing effect firstly
                        text_field_password.setEffect(null);
                        //removing effect firstly
                        text_field_confirm_password.setEffect(null);

                        //now that all fields have been checked allowing user to 
                        //enter themselves to the database
                        //as a new user
                        try {
                            boolean checkUserDouble = NewUser.addNewUser("name", username, password, email);

                            if (checkUserDouble == true) {
                                Alert a = new Alert(AlertType.INFORMATION);
                                a.setContentText("Your account has been created.");
                                a.show();

                                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                                stage.close();
                            } else {
                                text_field_username.setEffect(dropShadow);
                            }

                        } catch (Exception ex) {
                            Logger.getLogger(SignUpWindowController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        text_field_password.setEffect(dropShadow);
                        text_field_confirm_password.setEffect(dropShadow);
                    }

                } else {
                    text_field_password.setEffect(dropShadow);
                }

            } else {
                text_field_username.setEffect(dropShadow);
            }

            //System.out.println("the password is valid : " + IsValidPassword(text_field_password.getText()));
            //System.out.println("the password are same : " + isSamePassword(password, passwordCheck));
        } else {
            privacy_check_box.setEffect(dropShadow);
            //privacy_check_box.setTextFill(Color.RED);
            //System.out.println("you have NOT checked the mark");
        }

    }

    public static boolean IsValidUsername(String s) {
        if (s.length() <= 4) {
            return false;
        }

        for (int i = 0; i < s.length(); ++i) {
            if (Character.isWhitespace(s.charAt(i))) {
                return false;
            }

        }
        return true;
    }

    public static boolean IsValidPassword(String s) {
        if (s.length() < 8) {
            return false;
        }

        for (int i = 0; i < s.length(); ++i) {
            if (Character.isWhitespace(s.charAt(i))) {
                return false;
            }

        }
        return true;
    }

    public static boolean isSamePassword(String s1, String s2) {
        return s1.equals(s2);
    }
}
