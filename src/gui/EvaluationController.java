/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import services.inscriptionCRUD;

/**
 * FXML Controller class
 *
 * @author toufa
 */
public class EvaluationController implements Initializable {

    @FXML
    private Rating starts;
    @FXML
    private TextArea comment;
    @FXML
    private Button sub;
    @FXML
    private TextField email;

    List<String> badwords = new ArrayList<>();
    @FXML
    private FontAwesomeIconView RT;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        badwords.add("2 girls 1 cup");
        badwords.add("alaskan pipeline");
        badwords.add("anal");
        badwords.add("anus");
        badwords.add("apeshit");
        badwords.add("ass");
        badwords.add("asshole");
        badwords.add("ball sucking");
        badwords.add("bastard");
        badwords.add("bdsm");
        badwords.add("big tits");
        badwords.add("bitch");
        badwords.add("bitches");
        badwords.add("boob");
        badwords.add("boobs");
        badwords.add("bullshit");
        badwords.add("cock");
        badwords.add("faggot");
        badwords.add("fuck");
        badwords.add("fucking");
        badwords.add("god damn");
        badwords.add("how to kill");
        badwords.add("nigga");
        badwords.add("nigger");
        badwords.add("penis");
        badwords.add("rape");
        badwords.add("shit");
        badwords.add("shitty");
       
        
        
        
        
    }    

    @FXML
    private void submit(ActionEvent event) {
        double s=starts.getRating();
        String str=comment.getText();
        String e=email.getText();
        if (containsBadWord(str)==false){
        inscriptionCRUD ins = new inscriptionCRUD();
        ins.updateInscription(e,s,str);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thank You!");
        alert.setHeaderText("Evaluation Submitted");
        alert.setContentText("Thank you for your contribution in evaluating the competition.");
        alert.showAndWait();
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        }
        else{
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Invalid Comment");
    alert.setHeaderText(null);
    alert.setContentText("Please enter a comment without inappropriate content. ");
    alert.showAndWait();
    comment.setText("");
        }
    }
        private  boolean containsBadWord(String comment) {
        for (String badword : badwords) {
            if (comment.contains(badword)) {
                return true; 
            }
        }
        return false;
    }

    @FXML
    private void BACK(MouseEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }
}
