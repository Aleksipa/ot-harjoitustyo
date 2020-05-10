/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodoroapp.domain;

import java.time.LocalTime;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Duration;

/**
 *
 * @author aleksipaavola
 */
public class Pomodoro {
    
    private int id;
    private int count;
    private int startTime = 1;
    private User user;
    private LocalTime time;
    public static IntegerProperty timeSeconds = new SimpleIntegerProperty();
    
    public Pomodoro(int id, int count, Integer startTime, SimpleIntegerProperty timeSeconds, User user) {
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
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }
    public void initializeTime() {
        time = LocalTime.of(0, startTime, 0);
    }
    /**
     * @return the time
     */
    public LocalTime getTime() {
        return time;
    }
    /**
     * @return the timeSeconds
     */
    public int getTimeInSeconds() {
        return new SimpleIntegerProperty(startTime * 60).get() + 1;
    }
    /**
     * @param time the time to set
     */
    public void setTime(LocalTime time) {
	this.time = time;
    }
    /**
     * @param add the count
     */
    public void addCount() {
        this.count ++;
    }
    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }
    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }
    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
}
