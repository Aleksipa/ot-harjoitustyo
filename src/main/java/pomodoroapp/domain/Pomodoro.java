/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodoroapp.domain;

import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;

/**
 *
 * @author aleksipaavola
 */
public class Pomodoro {
    
    private int id;
    private int count;
    private Integer startTime;
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(10);
    private User user;
    
    
    public Pomodoro(int id, int count, Integer startTime, IntegerProperty timeSeconds, User user) {
        this.id = id;
        this.count = count;
        this.startTime = startTime;
        this.timeSeconds = timeSeconds;
        this.user = user;
    }
    
    public Pomodoro(int count, User user) {
        this.count = count;
        this.user = user;
    }
    
        public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public User getUser() {
        return user;
    }

    /**
     * @return the startTime
     */
    public Integer getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
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

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param countor the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }
    
    public void addCount() {
        this.count ++;
    }
    
}
