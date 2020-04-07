/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodoroapp.domain;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 *
 * @author aleksipaavola
 */
public class PomodoroService {
    
    /**
     *
     * @param STARTTIME
     * @param timeline
     * @param timerLabel
     * @param timeSeconds
     * @return
     */
    public boolean createPomodoro(Integer STARTTIME, Timeline timeline, Label timerLabel, IntegerProperty timeSeconds) {
        try {   
            if (timeline != null) {
                    timeline.stop();
                }
                timeSeconds.set(STARTTIME);
                timeline = new Timeline();
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(STARTTIME+1),
                        new KeyValue(timeSeconds, 0)));
                timeline.playFromStart();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    
}

