/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.points;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author toufa
 */
public class LeaderController implements Initializable {

    @FXML
    private AnchorPane prizeTxt;
    @FXML
    private TextField username;
    @FXML
    private TextField points;
    @FXML
    private Button addBtn;
    @FXML
    private AnchorPane backBtn;
    @FXML
    private Button returnBtn;
    @FXML
    private BarChart<String,Number> leaderboard;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                List<points> pointsList = yourDataRetrievalMethod();
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (points point : pointsList) {
            series.getData().add(new XYChart.Data<>(point.getUsername(), point.getPoints()));
        }
        series.setName("Points");
        leaderboard.getData().add(series);
        // TODO
    }    

    @FXML
    private void saveSport(ActionEvent event) {
            String inputUsername = username.getText();
    int inputPoints = Integer.parseInt(points.getText()); 
    int existingPoints = getPointsByUsername(inputUsername);

    if (existingPoints != -1) {
        int newPoints = existingPoints + inputPoints;
        updatePointsByUsername(inputUsername, newPoints);
    } else {
        insertPoints(inputUsername, inputPoints);
    }

    username.clear();
    points.clear();
    refreshLeaderboard();

    }

    @FXML
    private void goToMenu(ActionEvent event) {
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
    public List<points> yourDataRetrievalMethod() {
    List<points> pointsList = new ArrayList();

    try {
        String query = "SELECT id, username, points FROM points";
      PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            int points = resultSet.getInt("points");
            pointsList.add(new points(id, username, points));
        }



    } catch (SQLException e) {
        e.printStackTrace();
    }

    return pointsList;
}
    private int getPointsByUsername(String username) {
    int existingPoints = -1; 

    try {
        String query = "SELECT points FROM points WHERE username = ?";
        PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(query);
        preparedStatement.setString(1, username);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            existingPoints = resultSet.getInt("points");
        }
    } catch (SQLException e) {
        e.printStackTrace();

    }

    return existingPoints;
}

private void updatePointsByUsername(String username, int newPoints) {
    try {
        String query = "UPDATE points SET points = ? WHERE username = ?";
        PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(query);
        preparedStatement.setInt(1, newPoints);
        preparedStatement.setString(2, username);

        int rowsAffected = preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// Method to insert new points
private void insertPoints(String username, int points) {
    try {
        String query = "INSERT INTO points (username, points) VALUES (?, ?)";
        PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setInt(2, points);

        int rowsAffected = preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public void refreshLeaderboard() {

    leaderboard.getData().clear();

    List<points> pointsList = yourDataRetrievalMethod();

    XYChart.Series<String, Number> series = new XYChart.Series<>();

    for (points point : pointsList) {
        series.getData().add(new XYChart.Data<>(point.getUsername(), point.getPoints()));
    }

    series.setName("Points");

    leaderboard.getData().add(series);
}
    
}
