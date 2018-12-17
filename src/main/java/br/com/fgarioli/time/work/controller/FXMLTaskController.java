/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fgarioli.time.work.controller;

import br.com.fgarioli.time.work.model.Task;
import br.com.fgarioli.time.work.service.TaskService;
import br.com.fgarioli.javafx.custom.DateTimePicker;
import br.com.fgarioli.javafx.utils.Functions;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.util.Duration;
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
public class FXMLTaskController implements Initializable {

    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfDuration;
    @FXML
    private HTMLEditor tfDescr;
    @FXML
    private DateTimePicker dpDtStart;
    @FXML
    private DateTimePicker dpDtFinish;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btPlay;
    @FXML
    private Button btPause;

    private Integer taskId;

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private ApplicationContext springContext;

    private Timeline mTimeline;

    private Task task;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        dpDtFinish.setOnMouseClicked(e -> {
//            if (!dpDtFinish.isEditable()) {
//                dpDtFinish.hide();
//            }
//        });
//        dpDtStart.setOnMouseClicked(e -> {
//            if (!dpDtStart.isEditable()) {
//                dpDtStart.hide();
//            }
//        });
        mTimeline = new Timeline();

        // Limite de Tempo
        mTimeline.setCycleCount(Timeline.INDEFINITE);

        // Ação quando play
        mTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
            this.task.setDuration(this.task.getDuration().plus(java.time.Duration.ofSeconds(1)));
            tfDuration.setText(DurationFormatUtils.formatDuration(this.task.getDuration().toMillis(), "dd:HH:mm:ss"));
        }));

        // Ação Stop
        mTimeline.setOnFinished(e -> {
            save();
        });
    }

    private void save() {
        if (taskId == null) {
            task = taskService.save(getTask());            
        } else {
            task = taskService.update(getTask());
        }
    }

    public void newTask() {
        this.task = new Task();
        this.task.setDuration(java.time.Duration.ZERO);

        this.setTask(task);
    }

    public void editTask(Task task) {
        this.task = task;
        this.setTask(task);
    }

    @FXML
    private void btBackOnAction(ActionEvent event) throws IOException {
        save();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/FXMLMain.fxml"));
        fxmlLoader.setControllerFactory(this.springContext::getBean);
        AnchorPane a = (AnchorPane) fxmlLoader.load();
        FXMLMainController mainController = fxmlLoader.<FXMLMainController>getController();
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    private void btFinishOnAction(ActionEvent event) {
        task.setFinishDate(LocalDateTime.now());
        save();
    }

    @FXML
    private void btPlayOnAction(ActionEvent event) {
        btPlay.setVisible(false);
        btPause.setVisible(true);
        this.mTimeline.play();
    }

    @FXML
    private void btPauseOnAction(ActionEvent event) {
        btPlay.setVisible(true);
        btPause.setVisible(false);
        this.mTimeline.pause();
    }

    private void setTask(Task t) {
        this.taskId = t.getIdTask();
        this.tfTitle.setText(t.getTitle());
        this.tfDuration.setText(DurationFormatUtils.formatDuration(t.getDuration().toMillis(), "dd:HH:mm:ss"));
        this.tfDescr.setHtmlText(t.getDescription());
        this.dpDtStart.setDateTimeValue(t.getStartDate());
        this.dpDtFinish.setDateTimeValue(t.getFinishDate());
    }

    private Task getTask() {
        Task t = new Task();
        t.setIdTask(taskId);
        t.setTitle(tfTitle.getText());
        t.setDuration(Functions.stringToDuration(tfDuration.getText()));
        t.setDescription(tfDescr.getHtmlText());
        t.setStartDate(dpDtStart.getDateTimeValue());
        t.setFinishDate(dpDtFinish.getDateTimeValue());

        return t;
    }

}
