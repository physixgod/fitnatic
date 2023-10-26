/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.MyConnection;

/**
 *
 * @author toufa
 */
public class inscriptionCRUD {
public void insertInscription(String email, String competition) {
    String evaluation = "Not evaluated yet";
    double rating = -1.0;

    try {
        String query = "INSERT INTO Inscription (email, competition, evaluation,rating) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, competition);
        preparedStatement.setString(3, evaluation);
        preparedStatement.setDouble(4, rating);
        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Insertion successful");
        } else {
            System.out.println("Insertion failed");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
 public void updateInscription(String email, double rating, String evaluation) {
        String query = "UPDATE Inscription SET rating = ?, evaluation = ? WHERE email = ?";
        try (
            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(query)) {
            preparedStatement.setDouble(1, rating);
            preparedStatement.setString(2, evaluation);
            preparedStatement.setString(3, email);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Inscription updated successfully.");
            } else {
                System.out.println("Failed to update Inscription.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
