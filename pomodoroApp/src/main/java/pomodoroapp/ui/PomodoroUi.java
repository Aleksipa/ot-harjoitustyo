/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodoroapp.ui;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import pomodoroapp.domain.PomodoroService;
import pomodoroapp.domain.Pomodoro;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author aleksipaavola
 */
public class PomodoroUi extends Application {
    
    //Nämä vain testausta varten, olioita käyttämällä ei vielä toimi
    private static final Integer STARTTIME = 15;
    private Timeline timeline;
    private Label timerLabel = new Label();
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    
    private PomodoroService pomodoroService;
    private Pomodoro pomodoro;
    
    @Override
    public void init() {
        
        pomodoroService = new PomodoroService();
        
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 4em;");
        
        Button startButton = new Button("Start");
        startButton.setOnAction(e-> {
            if (timeline != null) {
                    timeline.stop();
                }
                timeSeconds.set(STARTTIME);
                timeline = new Timeline();
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(STARTTIME+1),
                        new KeyValue(timeSeconds, 0)));
                timeline.playFromStart();
            
        });

        
        GridPane vb = new GridPane();
         
        vb.add(timerLabel, 0, 0);
        vb.add(startButton, 1, 0);
        
        vb.setHgap(10);
        vb.setVgap(10);
        vb.setPadding(new Insets(40, 100, 40, 100));
        
        Scene scene = new Scene(vb);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pomodoro App");
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
   
