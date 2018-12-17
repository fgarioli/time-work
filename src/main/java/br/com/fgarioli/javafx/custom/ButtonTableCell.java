/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fgarioli.javafx.custom;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import lombok.Getter;

/**
 *
 * @author fernando
 */
public class ButtonTableCell<S, T> extends TableCell<S, T> {

    @Getter
    private Button button;

    public ButtonTableCell(Button button, Node graphic) {
        this.button = new Button();        
        this.button.setOnAction(button.getOnAction());
        this.button.getStyleClass().addAll(button.getStyleClass());
        this.button.setGraphic(graphic);        
    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        this.setAlignment(Pos.CENTER);
        if (!empty) {
            setGraphic(button);
        } else {
            setGraphic(null);
        }
    }

}
