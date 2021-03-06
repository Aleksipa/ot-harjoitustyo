package pomodoroapp.domain;

import java.time.LocalTime;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleksipaavola
 */
public class PomodoroTest {
    
    @Test
    public void notEqualWhenDifferentId() {
        Pomodoro p1 = new Pomodoro(1, 1, 1, null, null);
        Pomodoro p2 = new Pomodoro(5, 1, 1, null, null);
        assertFalse(p1.equals(p2));
    }   
    
    @Test
    public void nonEqualWhenDifferentType() {
        Pomodoro p = new Pomodoro(1, 1, 1, null,null);
        Object o = new Object();
        assertFalse(p.equals(o));
    }   
    @Test
    public void nonEqualWhenDifferentTime() {
        Pomodoro p1 = new Pomodoro(1, 1, 1, null, null);
        Pomodoro p2 = new Pomodoro(1, 1, 1, null, null);
        p1.setTime(LocalTime.of(0, 2));
        assertFalse(p1.equals(p2));
    }
    @Test
    public void nonEqualWhenDifferentCount() {
        Pomodoro p1 = new Pomodoro(1, 1, 1, null, null);
        Pomodoro p2 = new Pomodoro(1, 1, 1, null, null);
        p1.addCount();
        assertFalse(p1.equals(p2));
    }
}
   
