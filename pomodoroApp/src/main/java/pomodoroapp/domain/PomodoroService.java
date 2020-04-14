/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodoroapp.domain;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.util.Duration;
import static javafx.util.Duration.ZERO;
import pomodoroapp.dao.PomodoroDao;
import pomodoroapp.dao.UserDao;

/**
 *
 * @author aleksipaavola
 */
public class PomodoroService {

    private PomodoroDao pomodoroDao;
    private UserDao userDao;
    private User loggedIn;
    
    public PomodoroService(PomodoroDao pomodoroDao, UserDao userDao) {
        this.userDao = userDao;
        this.pomodoroDao = pomodoroDao;
    }
    
        public boolean login(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return false;
        }
        
        loggedIn = user;
        
        return true;
    }
    
    public boolean createUser(String username, String name)  {   
        if (userDao.findByUsername(username) != null) {
            return false;
        }
        User user = new User(username, name);
        try {
            userDao.create(user);
        } catch(Exception e) {
            return false;
        }

        return true;
    }
    
    /**
    * uloskirjautuminen
    */  
    
    public void logout() {
        loggedIn = null;  
    }
    
    /**
    * kirjautuneena oleva käyttäjä
    * 
    * @return kirjautuneena oleva käyttäjä 
    */   
    
    public User getLoggedUser() {
        return loggedIn;
    }

    
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
                
                timeline.setOnFinished(e -> {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("It's time for a break");
                    alert.setHeaderText("You completed your pomodoro! It's time for a break.");
                    alert.setContentText("I have a great message for you!");

                    alert.show();
                });
                
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    
}

