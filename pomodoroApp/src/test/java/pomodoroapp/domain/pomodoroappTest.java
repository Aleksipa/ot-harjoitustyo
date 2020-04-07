/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodoroapp.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aleksipaavola
 */
public class pomodoroappTest {
    
    public pomodoroappTest() {
    }
    
    //@BeforeAll
    public static void setUpClass() {
    }
    
    //@AfterAll
    public static void tearDownClass() {
    }
    
    //@BeforeEach
    public void setUp() {
    }
    
    //@AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
   
  
    @Test
    public void notEqualWhenDifferentId() {
        Pomodoro p1 = new Pomodoro(1, null, null, null);
        Pomodoro p2 = new Pomodoro(1, null, null, null);
        assertFalse(p1.equals(p2));
    }   
    
    @Test
    public void nonEqualWhenDifferentType() {
        Pomodoro p = new Pomodoro(1, null, null, null);
        Object o = new Object();
        assertFalse(p.equals(o));
    }      
}
