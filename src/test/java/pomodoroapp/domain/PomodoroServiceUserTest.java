/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodoroapp.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author aleksipaavola
 */
public class PomodoroServiceUserTest {
    
    FakePomodoroDao pomodoroDao;
    FakeUserDao userDao;
    PomodoroService service;
    
    @Before
    public void setUp() {
        pomodoroDao = new FakePomodoroDao();
        userDao = new FakeUserDao();
        service = new PomodoroService(pomodoroDao, userDao);     
    }
    
    @Test
    public void nonExistingUserCanLogIn() {
        boolean result = service.login("nonexisting");
        assertFalse(result);
        
        assertEquals(null, service.getLoggedUser());
    }    
    
    @Test
    public void existingUserCanLogIn() {
        boolean result = service.login("testertester");
        assertTrue(result);
        
        User loggedIn = service.getLoggedUser();
        assertEquals("Teppo Testaaja", loggedIn.getName() );
    }
    
    @Test
    public void loggedInUserCanLogout() {
        service.login("testertester");
        service.logout();
        
        assertEquals(null, service.getLoggedUser());
    }    
    
    @Test
    public void userCreationFailsIfNameNotUnique() throws Exception {
        boolean result = service.createUser("testertester", "Teuvo Testaaja");
        assertFalse(result);
    }
    
    @Test
    public void succesfullyCreatedUserCanLogIn() throws Exception {
        boolean result = service.createUser("dijkstra", "Edsger Dijkstra");
        assertTrue(result);
        
        boolean loginOk = service.login("dijkstra");
        assertTrue(loginOk);
        
        User loggedIn = service.getLoggedUser();
        assertEquals("Edsger Dijkstra", loggedIn.getName() );
    } 
}
