/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingresizing;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import static javafx.scene.input.DataFormat.URL;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Abdullah
 */
public class ChangeScenes {
    //changes the display in borderpane center

    public static void moveToCenter(URL ui, BorderPane borderpane) {
        Parent root = null;
        try {
            root = FXMLLoader.load(ui);
        } catch (IOException ex) {
            Logger.getLogger(ChangeScenes.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpane.setCenter(root);
    }
}
