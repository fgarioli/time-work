/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fgarioli.javafx.custom;

import com.rits.cloning.Cloner;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author fernando
 */
public class ButtonTableCellFactory<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

    @Getter
    ButtonTableCell tableCell;

    @Getter
    @Setter
    private Button button;
    
    @Getter
    @Setter
    private MaterialIconView icon;

    @Override
    public TableCell<S, T> call(TableColumn<S, T> param) {
        MaterialIconView mIconView = new MaterialIconView(MaterialIcon.valueOf(icon.getGlyphName()));
        mIconView.setFill(icon.getFill());
        mIconView.setSize(icon.getSize());
        this.tableCell = new ButtonTableCell<>(this.button, mIconView);
        return this.tableCell;
    }

}
