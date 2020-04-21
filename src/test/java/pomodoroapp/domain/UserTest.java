/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodoroapp.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author aleksipaavola
 */
public class UserTest {
    
    @Test
    public void equalWhenSameUsername() {
        User u1 = new User("tester", "Teppo");
        User u2 = new User("tester", "Teppo");
        assertTrue(u1.equals(u2));
    }
    
    @Test
    public void nonEqualWhenDifferentUsername() {
        User u1 = new User("tester", "Teppo");
        User u2 = new User("hellas", "Arto");
        assertFalse(u1.equals(u2));
    } 
    
    @Test
    public void nonEqualWhenDifferentType() {
        User u = new User("tester", "Teppo");
        Object o = new Object();
        assertFalse(u.equals(o));
    }     
}