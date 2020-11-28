/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingresizing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Abdullah
 */
public class MainPageController implements Initializable {

    @FXML
    private BorderPane root_pane;
    @FXML
    private Circle circle_transition;
    @FXML
    private Button prev_results_btn;
    @FXML
    private Button settings_btn;
    @FXML
    private Button about_btn;
    @FXML
    private Button learn_btn;
    @FXML
    private Button start_test_btn;
    @FXML
    private ProgressBar progress_bar;
    @FXML
    private Tooltip progress_tool_tip;
    @FXML
    private VBox leaderboard_pane;
    @FXML
    private Button sign_up_btn1;
    @FXML
    private Button login_btn;
    @FXML
    private TextField text_field_username;
    @FXML
    private PasswordField text_field_password;
    @FXML
    private Label top1_label;
    @FXML
    private Label top2_label;
    @FXML
    private Label top3_label;
    @FXML
    private TextField text_field_feedback;
    //a check for whether user is logged in 
    private boolean loggedInCheck;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //System.out.println("goal : " + SettingsPageController.goalgoal);
        //calculating the progress using best score / target
        double setThisProgress = 20 / SettingsPageController.goalgoal;
        //System.out.println(progress_bar.getProgress());

        //setting the score to progress bar and tooltip
        progress_bar.setProgress(setThisProgress);
        int progress = (int) (progress_bar.getProgress() * 100);
        String progressToolTip = String.valueOf(progress);
        progress_tool_tip.setText(progressToolTip + "% achieved");

        //connecting the leaderboard pane to the databse
        //so that the newest data is shown for the top three
        try {
            Leaderboard.leaderboards();
        } catch (Exception ex) {
            Logger.getLogger(LeaderBoardPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[][] array = Leaderboard.returnArrLeaderboard();
        top1_label.setText(array[0][0]);
        top2_label.setText(array[1][0]);
        top3_label.setText(array[2][0]);
//        top1_label.setText("zainsajid");
//        top2_label.setText("abdullahshafqat");
//        top3_label.setText("jawadali");

        //this code makes sure that the user logged in stays logged in
        String loggedInUserInfo = "";
        try {
            File myObj = new File("loginUserCredential.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                loggedInUserInfo = myReader.nextLine();
                //System.out.println(loggedInUserInfo);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            //System.out.println("An error occurred.");
            //e.printStackTrace();
        }
        //if the user info is stored then dont show login option
        if (loggedInUserInfo.isEmpty()) {
            //System.out.println("do noting");
        } else {
            //System.out.println("the person has logged in");
            //System.out.println(loggedInUserInfo);
            text_field_username.clear();
            login_btn.setText("Logout");
            text_field_username.setText(loggedInUserInfo);
            text_field_username.setAlignment(Pos.CENTER);
            text_field_password.setVisible(false);
            text_field_username.setEditable(false);
            text_field_password.setEditable(false);
        }
        
    }

    public void back(ActionEvent event) {
        ChangeScenes.moveToCenter(getClass().getResource("PrevResultsPage.fxml"), root_pane);
    }

    /*
    this is where all the page switching buttons are handled
    including the page switching pane
     */
    @FXML
    private void gotoPrevResultPage(ActionEvent event) {
        transitionFunction("PrevResultsPage", "#34cdcc");
    }

    @FXML
    private void gotoSettingsPage(ActionEvent event) {
        //transitionFunction("SettingsPage", "#34cdcc");
    }

    @FXML
    private void gotoAboutPage(ActionEvent event) {
        //transitionFunction("AboutPage", "#34cdcc");
    }

    @FXML
    private void gotoLearnPage(ActionEvent event) {
        transitionFunction("LearnPage_1", "#34cdcc");
    }

    //this is for a pane and is not a action event
    //but a mouse event on the pane itself
    @FXML
    private void click_leaderboard_pane(MouseEvent event) {
        //System.out.println("the leaderboard pane has been clicked");
        transitionFunction("LeaderboardPage", "#34cdcc");
    }

    @FXML
    private void gotoStartTestPage(ActionEvent event) {
        transitionFunction("StartTestPage", "#0f9d58");
    }

    //seems like useless
//    private void gotoProfilePage(ActionEvent event) {
//        transitionFunction("ProfilePage", "#e34234");
//    }
    private void transitionFunction(String id, String hexCode) {
        //System.out.println("GOING TO PAGE : " + id);
        ScaleTransition st = new ScaleTransition(Duration.millis(1000), circle_transition);
        st.setByX(100f);
        st.setByY(100f);
        st.setAutoReverse(true);
        //Instantiating FadeTransition class   
        FadeTransition fade = new FadeTransition();
        //setting the duration for the Fade transition   
        fade.setDuration(Duration.millis(500));
        //setting the initial and the target opacity value for the transition   
        fade.setFromValue(0);
        fade.setToValue(1);

        //setting up the backing up transition
        ScaleTransition stBack = new ScaleTransition(Duration.millis(250), circle_transition);
        stBack.setByX(-100);
        stBack.setByY(-100);
        stBack.setAutoReverse(true);
        //setting up the fade out transition
        FadeTransition fadeBack = new FadeTransition();
        fadeBack.setDuration(Duration.millis(250));
        fadeBack.setFromValue(1);
        fadeBack.setToValue(0);

        //setting the color for the next page basically
        //circle_transition.setFill(Color.web(hexCode));
//        RadialGradient gradient1 = new RadialGradient(0,
//                0,
//                0,
//                0,
//                10,
//                false,
//                CycleMethod.NO_CYCLE,
//                new Stop(0, Color.web("#8ce6b4")),
//                new Stop(1, Color.web("#5cdb95")));
//        RadialGradient gradient2 = new RadialGradient(0,
//                0,
//                0,
//                0,
//                10,
//                false,
//                CycleMethod.NO_CYCLE,
//                new Stop(0, Color.web("#ea7268")),
//                new Stop(1, Color.web("#e34234")));
//        if (hexCode.equals("#e34234")) {
//            //circle_transition.setFill(gradient2);
//            circle_transition.setFill(Color.web("#34cdcc"));
//        } else {
//            //circle_transition.setFill(gradient1);
//            circle_transition.setFill(Color.web("#34cdcc"));
//        }
        circle_transition.setFill(Color.web(hexCode));
        //setting cycle count for the Fade transition   
        //fade.setCycleCount(1000);  
        //the transition will set to be auto reversed by setting this to true   
        fade.setAutoReverse(true);
        //setting Circle as the node onto which the transition will be applied  
        fade.setNode(circle_transition);
        //playing the transition   
        fade.play();
        //circle_transition.setOpacity(1);
        st.play();
        st.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                //System.out.println("the circle dude should be going back");
                try {
                    setRoot(id);
                    fadeBack.play();
                    stBack.play();
                } catch (IOException ex) {
                    Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        stBack.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                circle_transition.setOpacity(0);
            }
        ;
    }

    );

    }

    private void setRoot(String id) throws IOException {
        Parent root;
        try {
            //Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource(id + ".fxml"));
            root_pane.setCenter(root);

        } catch (IOException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void shakeNode() {

    }

    @FXML
    private void launchSignUpStage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignUpWindow.fxml"));
        Scene scene = new Scene(root);
        Stage signUpStage = new Stage();
        signUpStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);

        signUpStage.setTitle("Sign Up");
        signUpStage.setScene(scene);
        signUpStage.show();

        final MainPageController.Delta dragDelta = new MainPageController.Delta();
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x = signUpStage.getX() - mouseEvent.getScreenX();
                dragDelta.y = signUpStage.getY() - mouseEvent.getScreenY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                signUpStage.setX(mouseEvent.getScreenX() + dragDelta.x);
                signUpStage.setY(mouseEvent.getScreenY() + dragDelta.y);
            }
        });
    }

    /*
    handling the hover label color changing for the 
    leaderboard pane
     */
    @FXML
    private void mouse_enter_leaderboard(MouseEvent event) {
        //System.out.println("mouse has entered the leaderboard pane");
        top1_label.setTextFill(Color.BLACK);
        top2_label.setTextFill(Color.BLACK);
        top3_label.setTextFill(Color.BLACK);
        
    }

    @FXML
    private void mouse_exit_leaderboard(MouseEvent event) {
        //System.out.println("mouse has exited the leaderboard pane");
        top1_label.setTextFill(Color.WHITE);
        top2_label.setTextFill(Color.WHITE);
        top3_label.setTextFill(Color.WHITE);
    }

    @FXML
    private void loginBtnClicked(ActionEvent event) {
        if (login_btn.getText().equals("Logout")) {
            login_btn.setText("Login");
            text_field_username.setEditable(true);
            text_field_username.clear();
            text_field_username.setAlignment(Pos.CENTER_LEFT);
            text_field_password.setVisible(true);
            text_field_password.setEditable(true);
            //System.out.println("the person has logged out");

            //clearing the user credential from the file
            //essentially logging them out
            try {
                FileWriter myWriter = new FileWriter("loginUserCredential.txt");
                myWriter.write("");
                myWriter.close();
                //System.out.println("Successfully deleted to the file.");
            } catch (IOException e) {
                //System.out.println("An error occurred while deleting the username to the username store file.");
                //e.printStackTrace();
            }
        }

        String username = text_field_username.getText();
        String password = text_field_password.getText();
        text_field_password.clear();

        try {
            loggedInCheck = SignIn.signIn(username, password);
        } catch (Exception ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (loggedInCheck == true) {
            //System.out.println("the person has logged in");
            //System.out.println(username + " " + password);
            text_field_username.clear();
            login_btn.setText("Logout");
            text_field_username.setText(username);
            text_field_username.setAlignment(Pos.CENTER);
            text_field_password.setVisible(false);
            text_field_username.setEditable(false);
            text_field_password.setEditable(false);

            //writing to the file the username so that it can be retrieved
            //throughout the run of the program
            try {
                FileWriter myWriter = new FileWriter("loginUserCredential.txt");
                myWriter.write(username);
                myWriter.close();
                //System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                //System.out.println("An error occurred while writing the username to the username store file.");
                //e.printStackTrace();
            }
        }
    }

    @FXML
    private void getFeedback(ActionEvent event) {
        text_field_feedback.clear();
    }

    class Delta {

        double x, y;
    }
}
