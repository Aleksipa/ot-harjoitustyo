package pomodoroapp.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import pomodoroapp.domain.FakeUserDao;
import pomodoroapp.domain.Pomodoro;
import pomodoroapp.domain.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleksipaavola
 */
public class FilePomodoroDaoTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();    
  
    File userFile;  
    PomodoroDao dao;  
    
    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("testfile_users.txt");  
        UserDao userDao = new FakeUserDao();
        userDao.create(new User("testaaja", "Testi Kayttaja"));
        
        try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
            file.write("1;testaaja\n");
        }
        
        dao = new FilePomodoroDao(userFile.getAbsolutePath(), userDao);        
    }
    
    @Test
    public void pomodorosAreReadCorrectlyFromFile() {
        List<Pomodoro> pomodoros = dao.getAll();
        assertEquals(1, pomodoros.size());
        Pomodoro pomodoro = pomodoros.get(0);
        assertEquals(1, pomodoro.getCount());
        assertEquals("testaaja", pomodoro.getUser().getUsername());
    }  
        @After
    public void tearDown() {
        userFile.delete();
    }
    
}
