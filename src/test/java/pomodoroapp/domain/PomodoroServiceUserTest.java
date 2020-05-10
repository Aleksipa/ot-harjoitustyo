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
    PomodoroService pomodoroService;
    
    @Before
    public void setUp() {
        pomodoroDao = new FakePomodoroDao();
        userDao = new FakeUserDao();
        pomodoroService = new PomodoroService(pomodoroDao, userDao);     
    }
    
    @Test
    public void nonExistingUserCanLogIn() {
        boolean result = pomodoroService.login("eiole");
        assertFalse(result);
        
        assertEquals(null, pomodoroService.getLoggedUser());
    }    
    
    @Test
    public void existingUserCanLogIn() {
        boolean result = pomodoroService.login("testaaja");
        assertTrue(result);
        
        User loggedIn = pomodoroService.getLoggedUser();
        assertEquals("Testi Kayttaja", loggedIn.getName() );
    }
    
    @Test
    public void loggedInUserCanLogout() {
        pomodoroService.login("testaaja");
        pomodoroService.logout();
        
        assertEquals(null, pomodoroService.getLoggedUser());
    }    
    
    @Test
    public void userCreationFailsIfNameNotUnique() throws Exception {
        boolean result = pomodoroService.createUser("testaaja", "Testi Kayttaja");
        assertFalse(result);
    }
    
    @Test
    public void succesfullyCreatedUserCanLogIn() throws Exception {
        boolean result = pomodoroService.createUser("eka", "toka");
        assertTrue(result);
        
        boolean loginOk = pomodoroService.login("eka");
        assertTrue(loginOk);
        
        User loggedIn = pomodoroService.getLoggedUser();
        assertEquals("toka", loggedIn.getName() );
    } 
}
