/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import services.inscriptionCRUD;
import entities.Competition;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.CompetitionCRUD;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author toufa
 */
public class FormController implements Initializable {

    @FXML
    private AnchorPane backBtn;
    @FXML
    private Button back;
    @FXML
    private TextField Competition1;
    @FXML
    private TextField start1;
    @FXML
    private TextField end1;
    @FXML
    private TextField category1;
    @FXML
    private TextField entryfee1;
    @FXML
    private TextField participants1;
    @FXML
    private TextField prize1;
    @FXML
    private Button rd1;
    @FXML
    private Button ev1;
    @FXML
    private Button p1;
    @FXML
    private TextField location1;
    @FXML
    private TextField Competition2;
    @FXML
    private TextField Competition111;
    @FXML
    private TextField start2;
    @FXML
    private TextField end2;
    @FXML
    private TextField category2;
    @FXML
    private TextField entryfee2;
    @FXML
    private TextField participants2;
    @FXML
    private TextField prize2;
    @FXML
    private Button rd2;
    @FXML
    private Button ev2;
    @FXML
    private Button p2;
    @FXML
    private TextField location2;
    @FXML
    private TextField sport1;
    @FXML
    private TextField sport2;
    List<Competition> lastTwoOpenCompetitions = new ArrayList<>();
    private static final String YOUR_EMAIL = "fitnatic3@gmail.com"; 
    private static final String YOUR_PASSWORD = "23045265danke";



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        populateTextAreas();
        // TODO
    }  
    public void populateTextAreas() {
        

    try {
        String query = "SELECT * FROM Competition WHERE status = 'open' ORDER BY id DESC LIMIT 2";
        PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Competition c = new Competition();
            c.setId(resultSet.getInt("id"));
            c.setName(resultSet.getString("name"));
            c.setStartDate(resultSet.getDate("start_date"));
            c.setEndDate(resultSet.getDate("end_date"));
            c.setLocation(resultSet.getString("location"));
            c.setDescription(resultSet.getString("description"));
            c.setCompetitionCategory(resultSet.getString("competitionCategory"));
            c.setEntryFee(resultSet.getInt("entryFee"));
            c.setMaxParticipants(resultSet.getInt("maxParticipants"));
            c.setStatus(resultSet.getString("status"));
            c.setPrizes(resultSet.getString("prize"));
            c.setSportType(resultSet.getString("sportType"));
            lastTwoOpenCompetitions.add(c);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
        if (lastTwoOpenCompetitions != null && lastTwoOpenCompetitions.size() >= 2) {
    Competition competition1 = lastTwoOpenCompetitions.get(0);
    Competition competition2 = lastTwoOpenCompetitions.get(1);

            Competition1.setText(competition1.getName());
            start1.setText(competition1.getStartDate().toString());
            end1.setText(competition1.getEndDate().toString());
            category1.setText(competition1.getCompetitionCategory());
            entryfee1.setText(Integer.toString(competition1.getEntryFee()));
            participants1.setText(Integer.toString(competition1.getMaxParticipants()));
            prize1.setText(competition1.getPrizes());
            location1.setText(competition1.getLocation());
            sport1.setText(competition1.getSportType());

            Competition2.setText(competition2.getName());
            start2.setText(competition2.getStartDate().toString());
            end2.setText(competition2.getEndDate().toString());
            category2.setText(competition2.getCompetitionCategory());
            entryfee2.setText(Integer.toString(competition2.getEntryFee()));
            participants2.setText(Integer.toString(competition2.getMaxParticipants()));
            prize2.setText(competition2.getPrizes());
            location2.setText(competition2.getLocation());
            sport2.setText(competition2.getSportType());
        }
    }

    @FXML
    private void Menu(ActionEvent event) {
            try {
 
        Parent page =FXMLLoader.load(getClass().getResource("menuCompetition.fxml"));
        Scene scene=new Scene(page);
        Stage appStage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    } catch (IOException e) {
        e.printStackTrace();

    }
    }

    @FXML
    private void readmore1(ActionEvent event) {
    String description = lastTwoOpenCompetitions.get(0).getDescription(); 
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Competition Description");
    alert.setHeaderText("Description of "+Competition1.getText());
    alert.setContentText(description);
    alert.showAndWait();
    }

    @FXML
    private void evaluate1(ActionEvent event) {
     try {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("evaluation.fxml"));
        Parent page = loader.load();
        Stage evaluationStage = new Stage();
        evaluationStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(page);
        evaluationStage.setScene(scene);
        evaluationStage.show(); 
        // Close the current stage (optional)
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void participate1(ActionEvent event) {
    TextInputDialog emailDialog = new TextInputDialog();
    emailDialog.setTitle("Participate in " + Competition1.getText());
    emailDialog.setHeaderText("Enter Your Email");
    emailDialog.setContentText("Email:");

    emailDialog.showAndWait().ifPresent(email -> {
        String msg="Dear participant,\n\nThank you for joining "+Competition1.getText()+"! Your participation is greatly appreciated, and we look forward to your active involvement. If you have any questions or need assistance, please don't hesitate to contact us.\n\nBest regards,\nThe Competition Team";
        sendConfirmationEmail(email,msg);
        inscriptionCRUD ins = new inscriptionCRUD();
        ins.insertInscription(email,Competition2.getText());
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Participation Confirmation");
        alert.setHeaderText("Thank you for participating!");
        alert.setContentText("An email has been sent to " + email);
        alert.showAndWait();
    });
    }

    @FXML
    private void readmore2(ActionEvent event) {
            String description = lastTwoOpenCompetitions.get(1).getDescription(); 
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Competition Description");
    alert.setHeaderText("Description of "+Competition2.getText());
    alert.setContentText(description);
    alert.showAndWait();
    }

    @FXML
    private void evaluate2(ActionEvent event) {
        try {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("evaluation.fxml"));
        Parent page = loader.load();
        Stage evaluationStage = new Stage();
        evaluationStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(page);
        evaluationStage.setScene(scene);
        evaluationStage.show(); 
        // Close the current stage (optional)
    } catch (IOException e) {
        e.printStackTrace();
    } 
        
    }

    @FXML
    private void participate2(ActionEvent event) {
        TextInputDialog emailDialog = new TextInputDialog();
    emailDialog.setTitle("Participate in " + Competition2.getText());
    emailDialog.setHeaderText("Enter Your Email");
    emailDialog.setContentText("Email:");

    emailDialog.showAndWait().ifPresent(email -> {
         String msg="Dear participant,\n\nThank you for joining "+Competition2.getText()+"! Your participation is greatly appreciated, and we look forward to your active involvement. If you have any questions or need assistance, please don't hesitate to contact us.\n\nBest regards,\nThe Competition Team";
         
        sendConfirmationEmail(email,msg);
        inscriptionCRUD ins = new inscriptionCRUD();
        ins.insertInscription(email,Competition2.getText());
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Participation Confirmation");
        alert.setHeaderText("Thank you for participating!");
        alert.setContentText("An email has been sent to " + email);
        alert.showAndWait();
    });
    }
    public static void sendConfirmationEmail(String toEmail,String msg) {
      
        String host = "sandbox.smtp.mailtrap.io";
        String username = "633b3199b9af8f";
        String password = "2b8236475e4b84";
        String fromEmail = "admin@fitnatic.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "2525"); 

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
     
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Confirmation of Participation");
            message.setText(msg);

          
            Transport.send(message);

            System.out.println("Email sent successfully to: " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    
}
