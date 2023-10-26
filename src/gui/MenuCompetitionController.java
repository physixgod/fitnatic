/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author toufa
 */
public class MenuCompetitionController implements Initializable {

    @FXML
    private Button adc;
    @FXML
    private Button mc;
    @FXML
    private Button ans;
    @FXML
    private Button managesports;
    @FXML
    private Button competitions;
    @FXML
    private FontAwesomeIconView BACK;
    @FXML
    private Button led;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addcomp(ActionEvent event) {
                    try {
 
        Parent page =FXMLLoader.load(getClass().getResource("addCompetition.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    }
    }
    

    @FXML
    private void manage(ActionEvent event) {
                    try {
 
        Parent page =FXMLLoader.load(getClass().getResource("displayCompetiton.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    }
    }
    

    @FXML
    private void addsport(ActionEvent event) {
                            try {
 
        Parent page =FXMLLoader.load(getClass().getResource("addSport.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    }
    }

    @FXML
    private void editSports(ActionEvent event) {
                                    try {
 
        Parent page =FXMLLoader.load(getClass().getResource("manageSports.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    }
        
    }

    @FXML
    private void enterCompetitons(ActionEvent event) {
                                            try {
 
        Parent page =FXMLLoader.load(getClass().getResource("form.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    }
    }

    private void backtomenu(ActionEvent event) {
                                                    try {
 
        Parent page =FXMLLoader.load(getClass().getResource("menufitnatic.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    }
    }

    @FXML
    private void backtoMenu(MouseEvent event) {
                                                           try {
 
        Parent page =FXMLLoader.load(getClass().getResource("menufitnatic.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    }
    }

    @FXML
    private void gotoleaderboard(ActionEvent event) {
                                                        try {
 
        Parent page =FXMLLoader.load(getClass().getResource("Leader.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    }
    }
    
}
