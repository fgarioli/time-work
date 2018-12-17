/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fgarioli.javafx.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author fernando
 */
public class Alerts {
    
    private static void setIcon(Alert alert) {
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(Config.APP_IMAGE);
    }

    public static void alert(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        setIcon(alert);
        alert.setTitle(Config.APP_NAME);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        
        alert.showAndWait();
    }
    
    public static void alertObserv(String mensagem, String observ) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        setIcon(alert);
        alert.setTitle(Config.APP_NAME);
        alert.setHeaderText(mensagem);
        alert.setContentText(observ);

        alert.showAndWait();
    }

    public static Optional<ButtonType> questionAlert(String mensagem, String observacao) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        setIcon(alert);
        alert.setTitle(Config.APP_NAME);
        alert.setHeaderText(mensagem);
        alert.setContentText(observacao);
        Optional<ButtonType> result = alert.showAndWait();

        return result;
    }

    public static void alertError(String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        setIcon(alert);
        alert.setTitle(Config.APP_NAME);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);

        alert.showAndWait();
    }
}
