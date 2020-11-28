/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingresizing;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abdullah
 */
public class LeaderBoardPageController implements Initializable {

    @FXML
    private Button main_page_btn;
    @FXML
    private TableView<LeaderboardClass> leaderboard_table_view;
    @FXML
    private TableColumn<LeaderboardClass, String> rank_column;
    @FXML
    private TableColumn<LeaderboardClass, String> name_column;
    @FXML
    private TableColumn<LeaderboardClass, String> wpm_column;
    @FXML
    private TableColumn<LeaderboardClass, String> accuracy_column;

//    private final ObservableList<Leaderboard> data
//            = FXCollections.observableArrayList(
//                    new Leaderboard("1", "Zain Sajid", "80", "99.99"),
//                    new Leaderboard("2", "Jawad ", "79", "99.9"),
//                    new Leaderboard("3", "Abdullah", "60", "92")
//            );
    private ObservableList<LeaderboardClass> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try { 
            Leaderboard.leaderboards();
        } catch (Exception ex) {
            Logger.getLogger(LeaderBoardPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[][] array = Leaderboard.returnArrLeaderboard();

        data = FXCollections.observableArrayList();
        int counter = 1;
        for (int i = 0; i < 10; i++) { 
//            for (int j = 0; j < 3; j++) {
//                //System.out.println("arr[" + i + "][" + j + "] = " + array[i][j]);
//                data.add(new LeaderboardClass(Integer.toString(counter), "yolo", "80", "99.99"));
//            }
            data.add(new LeaderboardClass(Integer.toString(counter), array[i][0], array[i][1], array[i][2]));
            counter++;
        }
        
        rank_column.setCellValueFactory(
                new PropertyValueFactory<LeaderboardClass, String>("rank"));
        name_column.setCellValueFactory(
                new PropertyValueFactory<LeaderboardClass, String>("firstName"));
        wpm_column.setCellValueFactory(
                new PropertyValueFactory<LeaderboardClass, String>("wpm"));
        accuracy_column.setCellValueFactory(
                new PropertyValueFactory<LeaderboardClass, String>("accuracy"));
        leaderboard_table_view.setItems(data);
    }

    @FXML
    private void gotoMainPage(ActionEvent event) {
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

    public static class LeaderboardClass {

        private final SimpleStringProperty rank;
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty wpm;
        private final SimpleStringProperty accuracy;

        private LeaderboardClass(String rank1, String fName, String wpm1, String accuracy1) {
            this.firstName = new SimpleStringProperty(fName);
            this.rank = new SimpleStringProperty(rank1);
            this.wpm = new SimpleStringProperty(wpm1);
            this.accuracy = new SimpleStringProperty(accuracy1);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getRank() {
            return rank.get();
        }

        public void setRank(String rank1) {
            rank.set(rank1);
        }

        public String getWpm() {
            return wpm.get();
        }

        public void setWpm(String wpm1) {
            wpm.set(wpm1);
        }

        public String getAccuracy() {
            return accuracy.get();
        }

        public void setAccuracy(String accuracy1) {
            accuracy.set(accuracy1);
        }
    }
}
