/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodoroapp.domain;

import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;



/**
 *
 * @author aleksipaavola
 */
public class Pomodoro {
    
    private Integer STARTTIME;
    private Timeline timeline;
    private Label timerLabel;
    private IntegerProperty timeSeconds;
    
    public Pomodoro(Integer STARTTIME, Timeline timeline, Label timerLabel, IntegerProperty timeSeconds) {
        this.STARTTIME = STARTTIME;
        this.timeline = timeline;
        this.timerLabel = timerLabel;
        this.timeSeconds = timeSeconds;
    }

    /**
     * @return the STARTTIME
     */
    public Integer getSTARTTIME() {
        return STARTTIME;
    }

    /**
     * @param STARTTIME the STARTTIME to set
     */
    public void setSTARTTIME(Integer STARTTIME) {
        this.STARTTIME = STARTTIME;
    }

    /**
     * @return the timeline
     */
    public Timeline getTimeline() {
        return timeline;
    }

    /**
     * @param timeline the timeline to set
     */
    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    /**
     * @return the timerLabel
     */
    public Label getTimerLabel() {
        return timerLabel;
    }

    /**
     * @param timerLabel the timerLabel to set
     */
    public void setTimerLabel(Label timerLabel) {
        this.timerLabel = timerLabel;
    }

    /**
     * @return the timeSeconds
     */
    public IntegerProperty getTimeSeconds() {
        return timeSeconds;
    }

    /**
     * @param timeSeconds the timeSeconds to set
     */
    public void setTimeSeconds(IntegerProperty timeSeconds) {
        this.timeSeconds = timeSeconds;
    }
    
    
}
