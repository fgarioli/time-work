/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fgarioli.time.work.controller;

import br.com.fgarioli.time.work.model.Task;
import br.com.fgarioli.time.work.service.TaskService;
import br.com.fgarioli.javafx.custom.ButtonTableCell;
import br.com.fgarioli.javafx.custom.SimpleObservableValue;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

/**
 * FXML Controller class
 *
 * @author fernando
 */
@Controller
public class FXMLMainController implements Initializable {

    @FXML
    private TableView<Task> tbTask;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableColumn<Task, String> clDtStart;

    @FXML
    private TableColumn<Task, String> clDtFinish;

    @FXML
    private TableColumn<Task, String> clDuration;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ApplicationContext springContext;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tableConfig();
        tbTask.setItems(FXCollections.observableArrayList(taskService.findAll()));
    }

    public void tableConfig() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        clDtStart.setCellValueFactory((TableColumn.CellDataFeatures<Task, String> data) -> new SimpleObservableValue<>(data.getValue().getStartDate() == null ? "" : data.getValue().getStartDate().format(formatador)));
        clDtFinish.setCellValueFactory((TableColumn.CellDataFeatures<Task, String> data) -> new SimpleObservableValue<>(data.getValue().getFinishDate() == null ? "" : data.getValue().getFinishDate().format(formatador)));
        clDuration.setCellValueFactory((TableColumn.CellDataFeatures<Task, String> data) -> new SimpleObservableValue<>(DurationFormatUtils.formatDuration(data.getValue().getDuration().toMillis(), "HH:mm:ss")));
//        clEdit.setCellValueFactory((TableColumn.CellDataFeatures<Record, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));
//        clRemove.setCellValueFactory((TableColumn.CellDataFeatures<Record, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));

        // Impede a reordenação das colunas da tabela
        tbTask.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            final TableHeaderRow header = (TableHeaderRow) tbTask.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((o, oldVal, newVal) -> header.setReordering(false));
        });
        tbTask.setSelectionModel(null);
    }

    @FXML
    private void editTaskOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/FXMLTask.fxml"));
        fxmlLoader.setControllerFactory(this.springContext::getBean);
        AnchorPane a = (AnchorPane) fxmlLoader.load();
        FXMLTaskController taskController = fxmlLoader.<FXMLTaskController>getController();
        taskController.editTask(tbTask.getItems().get(((ButtonTableCell) ((Button) event.getSource()).getParent()).getIndex()));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    private void delTaskOnAction(ActionEvent event) {
        Task t = tbTask.getItems().get(((ButtonTableCell) ((Button) event.getSource()).getParent()).getIndex());
        taskService.delete(t);
        tbTask.getItems().remove(t);
    }

    @FXML
    private void btAddOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/FXMLTask.fxml"));
        fxmlLoader.setControllerFactory(this.springContext::getBean);
        AnchorPane a = (AnchorPane) fxmlLoader.load();
        FXMLTaskController taskController = fxmlLoader.<FXMLTaskController>getController();
        taskController.newTask();
        anchorPane.getChildren().setAll(a);
    }

}
