/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingresizing;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Abdullah
 */
public class StartTestPageController implements Initializable {

    @FXML
    private Button main_page_btn;
    @FXML
    private TextFlow text_flow_start;
    @FXML
    private TextField text_field_start;
    @FXML
    private Label timer_label;
    @FXML
    private Label wpm_label;
    @FXML
    private ProgressBar time_progress_bar;
    @FXML
    private Label accuracy_label;
    //private Button restart_typing_btn;
    @FXML
    private HBox start_test_hpane;
    @FXML
    private ChoiceBox<String> passage_combo_box;
    @FXML
    private ChoiceBox<String> time_combo_box;
    @FXML
    private Button restart_btn;

    //all the new variables
    //Declaring Varaibles
    private static int words = 0;
    private static int correctWords = 0;
    //final private static int MAXTIME = 10;
    private static int MAXTIME;
    private static int time;
    //private static int time = MAXTIME;
    private static Timeline timeline;
    //private static Label timer2;
    //private static Label wpm2;
    private static int wordsPerMin = 0;
    private static int accuracyVar = 0;
    private static int score = 0;
    private static boolean flag = true;
    private static int counterChar = 0;
    private static int correctChar = 0;
    private static int typedChar = 0;
    private static FileReader fr;
    private static boolean capsLock = Toolkit.getDefaultToolkit().getLockingKeyState(java.awt.event.KeyEvent.VK_CAPS_LOCK);
    private static int arrayCounter;
    private static String punctuation = "";
    //Extra variables for changing text
    private static String sampleText;
    private static String[] sampleTextArr;
    private static String[] charArr;
    //private static TextFlow textFlowPane;
    private static Text[] textUntyped;
    //private static ScrollPane sp;
    //private static TextField inputText;
    //private static Label accuracy2;

    //trying to send the results further
    public static int wpmDatabase;
    public static int accuracyDatabase;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //making the time combo box 
        MAXTIME = 60;
        time = MAXTIME;
        time_combo_box.getItems().addAll("30 Seconds", "1 Minute", "2 Minute", "5 Minute");
        time_combo_box.setValue("1 Minute");
        time_combo_box.setOnAction((e) -> {
            switch (time_combo_box.getValue()) {
                case "30 Seconds":
                    // code block
                    MAXTIME = 30;
                    break;
                case "2 Minute":
                    // code block
                    MAXTIME = 120;
                    break;
                case "5 Minute":
                    // code block
                    MAXTIME = 300;
                    break;
                default:
                    // code block
                    MAXTIME = 60;
            }

            time = MAXTIME;
            restart();
        });

        passage_combo_box.getItems().add("iPhone");
        passage_combo_box.getItems().add("YouTube");
        passage_combo_box.getItems().addAll("Warzone", "Star Wars", "Star Wars - Hard", "Red Riding Hood", "Java Code", "Words", "Words 2");

        passage_combo_box.setValue("Java Code");
        //System.out.println(passage_combo_box.getValue());
        passage_combo_box.setOnAction((e) -> {

            try {
                fr = new FileReader("paragraphs\\" + passage_combo_box.getValue() + ".txt");
                BufferedReader br = new BufferedReader(fr);

                //Creating array and storing text
                sampleText = br.readLine();
                sampleTextArr = sampleText.split(" ");
                charArr = sampleText.split("");

                //Code For TextFlowPane
                text_flow_start.getChildren().clear();
                //textFlowPane = new TextFlow();
                textUntyped = new Text[charArr.length];
                arrayCounter = 0;
                for (String c : charArr) {
                    textUntyped[arrayCounter] = new Text(c);
                    textUntyped[arrayCounter].setFont(new Font(15));
                    text_flow_start.getChildren().add(textUntyped[arrayCounter]);
                    arrayCounter++;
                }
                //sp.setContent(text_flow_start);
                restart();
            } catch (Exception ex) {
                //System.err.println("Exception: " + ex);
            }
        });

        //Reading File
        try {
            fr = new FileReader("paragraphs\\" + passage_combo_box.getValue() + ".txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StartTestPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader br = new BufferedReader(fr);

        //Creating array and storing text
        try {
            sampleText = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(StartTestPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        sampleTextArr = sampleText.split(" ");
        charArr = sampleText.split("");
        //System.out.println(Arrays.toString(charArr));

        //code for text flow pane
        textUntyped = new Text[charArr.length];
        arrayCounter = 0;
        for (String c : charArr) {
            textUntyped[arrayCounter] = new Text(c);
            textUntyped[arrayCounter].setFont(new Font(15));
            text_flow_start.getChildren().add(textUntyped[arrayCounter]);
            arrayCounter++;
        }

        //Code for when Key is Typed
        text_field_start.setOnKeyTyped(e -> {
            if (!Character.isLetter(e.getCharacter().charAt(0)) && e.isShiftDown()) {
                punctuation = e.getCharacter();
                //System.out.println("Punctuation: " + punctuation);
            }
        });

        //Code for when Key is Pressed
        text_field_start.setOnKeyPressed(event -> {
            //For checking characters
            /*System.out.println("getCode: " + event.getCode().toString());
            System.out.println("getText: " + event.getText());
            System.out.println("Against: " + charArr[counterChar]);*/
            if (event.getCode().equals(KeyCode.CAPS) && !capsLock) {
                capsLock = true;
            } else if (event.getCode().equals(KeyCode.CAPS) && capsLock) {
                capsLock = false;
            }
            if (counterChar < textUntyped.length) {
                if (event.getCode() == KeyCode.BACK_SPACE) {
                    if (counterChar - 1 >= 0) {
                        counterChar -= 1;
                        textUntyped[counterChar].setFill(Color.BLACK);
                        textUntyped[counterChar].setStrikethrough(false);
                    }
                } else if (event.getText().equals("") || event.getCode().equals(KeyCode.TAB)) {
                    //System.out.println("Ignore");
                } else {
                    if (event.getText().equals(charArr[counterChar]) && !event.isShiftDown()) {
                        textUntyped[counterChar].setFill(Color.web("rgb(52,205,204)"));
                        correctChar++;

                    } else if (event.getCode().toString().equals(charArr[counterChar])
                            && event.isShiftDown() && !capsLock) {
                        textUntyped[counterChar].setFill(Color.web("rgb(52,205,204)"));
                        correctChar++;

                    } else if (event.getText().toLowerCase().equals(charArr[counterChar])
                            && event.isShiftDown() && capsLock) {
                        textUntyped[counterChar].setFill(Color.web("rgb(52,205,204)"));
                        correctChar++;
                    } else if (!Character.isLetter(event.getText().charAt(0)) && event.isShiftDown()) {
                        if (punctuation.equals(charArr[counterChar])) {
                            textUntyped[counterChar].setFill(Color.web("rgb(52,205,204)"));
                            correctChar++;
                        } else {
                            textUntyped[counterChar].setFill(Color.RED);
                            textUntyped[counterChar].setStrikethrough(true);
                        }
                    } else {
                        textUntyped[counterChar].setFill(Color.RED);
                        textUntyped[counterChar].setStrikethrough(true);
                    }
                    typedChar++;
                    counterChar++;
                    if (counterChar >= textUntyped.length) {
                        showResult();
                        timeline.stop();
                    }
                }
            }

            if (event.getCode() == KeyCode.SPACE) {
                String typed = text_field_start.getText();
                String[] array = typed.split(" ");
                words++;

                if (array[(array.length) - 1].equals(sampleTextArr[(array.length) - 1])) {
                    correctWords++;
                } else {
                }
            }

            if (flag) {
                flag = false;
                timer_label.setText(String.format("%02d:%02d", (time) / 60, (time) % 60));
                timeline = new Timeline(new KeyFrame(
                        Duration.seconds(1),
                        ae -> {
                            //System.out.println("Time: " + time);
                            timer_label.setText(String.format("%02d:%02d", (time - 1) / 60, (time - 1) % 60));
                            double thisSet = time / (double) MAXTIME;
                            time_progress_bar.setProgress(thisSet);
                            time -= 1;
                            //System.out.println("Time After: " + time);
                            //System.out.println("Time: " + time + " WPM: " + (int) (((float) (typedChar / 5.0) / ((MAXTIME - time))) * 60));
                            wordsPerMin = (int) (((typedChar / 5.0) / ((MAXTIME - time))) * 60);
                            wpm_label.setText(String.format("%02d", wordsPerMin));
                            accuracyVar = (int) (((float) correctChar / typedChar) * 100);
                            accuracy_label.setText(String.format("%02d", accuracyVar));

                            //making variables to call the database function
                            wpmDatabase = (int) (((typedChar / 5.0) / ((MAXTIME - time))) * 60);
                            accuracyDatabase = (int) (((float) correctChar / typedChar) * 100);
                            if (time == 0) {
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

                                //adding the users score to his database
                                try {
                                    //System.out.println("adding the scores to the database");
                                    ScoreStorage.savingScore(userLoggedIn, wpmDatabase, accuracyDatabase);
                                } catch (Exception ex) {
                                    Logger.getLogger(StartTestPageController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                //getting the top scores of this user
                                try {
                                    //System.out.println("getting the top scores of this user");
                                    TopScoresRetrieval.getTopScore(userLoggedIn);
                                } catch (Exception ex) {
                                    Logger.getLogger(StartTestPageController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                showResult();
                            }
                        }));
                timeline.setCycleCount(MAXTIME);
                timeline.play();
            }
        });
    }

    @FXML
    private void gotoMainPage(ActionEvent event) {
        //resetting the test
        //restartTest();
        restart();

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

    @FXML
    private void startChecking(KeyEvent event) {

    }

    private void restart() {
        //this is what executes when the test ends

        time_progress_bar.setProgress(1);
        time = MAXTIME;
        timer_label.setText(String.format("%02d:%02d", time / 60, time % 60));
        wpm_label.setText(String.format("%02d", 0));
        accuracy_label.setText(String.format("%02d", 0));
        score = 0;
        if (!flag) {
            timeline.stop();
        }
        flag = true;
        counterChar = 0;
        correctChar = 0;
        typedChar = 0;
        arrayCounter = 0;
        wordsPerMin = 0;
        accuracyVar = 0;
        text_field_start.clear();
        for (String c : charArr) {
            textUntyped[arrayCounter].setFill(Color.BLACK);
            textUntyped[arrayCounter].setStrikethrough(false);
            arrayCounter++;
        }
    }

    private void showResult() {
        Stage stage = new Stage();
        stage.setTitle("Result");
        stage.initStyle(StageStyle.TRANSPARENT);

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("ResultsWindow.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(StartTestPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        final StartTestPageController.Delta dragDelta = new StartTestPageController.Delta();
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x = stage.getX() - mouseEvent.getScreenX();
                dragDelta.y = stage.getY() - mouseEvent.getScreenY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setX(mouseEvent.getScreenX() + dragDelta.x);
                stage.setY(mouseEvent.getScreenY() + dragDelta.y);
            }
        });
    }

    @FXML
    private void restartBtnAction(ActionEvent event) {
        restart();
    }

    class Delta {

        double x, y;
    }
}
