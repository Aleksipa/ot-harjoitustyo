/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodoroapp.domain;

import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author aleksipaavola
 */
public class PomodoroServicePomodoroTest {
    
    FakePomodoroDao pomodoroDao;
    FakeUserDao userDao;
    PomodoroService pomodoroService;
    
    @Before
    public void setUp() {
        pomodoroDao = new FakePomodoroDao();
        userDao = new FakeUserDao();
        User u1 = new User("testaaja1", "Testaaja ykkonen");
        User u2 = new User("testaaja2", "Testaaja kakkonen");
        userDao.create(u1);
        userDao.create(u2);        
        pomodoroDao.createOrUpdate(new Pomodoro(1, new User("testaaja1", "")));
        pomodoroService = new PomodoroService(pomodoroDao, userDao);     
        pomodoroService.login("tester1");
    }
    
    @Test
    public void pomodoroCountZeroIfNotLoggedIn() {
        pomodoroService.logout();
        int pomodoros = pomodoroService.getPomodoroCount();
        assertEquals(0, pomodoros);
    }  
    
    @Test
    public void loggedUsersCompletedPomodorosGetCount() {
        pomodoroService.login("testaaja1");
        int pomodoros = pomodoroService.getPomodoroCount();
        assertEquals(1, pomodoros);
    }
    @Test
    public void loggedUsersCompletedPomodorosAddCount() {
        pomodoroService.login("testaaja2");
        pomodoroService.completePomodoro(new Pomodoro(1,new User("testaaja2","")));
        int pomodoros = pomodoroService.getPomodoroCount();
        assertEquals(1, pomodoros);
    }
}
