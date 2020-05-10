package pomodoroapp.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
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
public class FileUserDaoTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File userFile;  
    UserDao dao;
    
    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("testfile_users.txt");  
        
        try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
            file.write("testaaja;Testi Kayttaja\n");
        }
        
        dao = new FileUserDao(userFile.getAbsolutePath());
    }
   
    @Test
    public void usersAreReadCorrectlyFromFile() {
        List<User> users = dao.getAll();
        assertEquals(1, users.size());
        User user = users.get(0);
        assertEquals("Testi Kayttaja", user.getName());
        assertEquals("testaaja", user.getUsername());
    }
    
    @Test
    public void existingUserIsFound() {
        User user = dao.findByUsername("testaaja");
        assertEquals("Testi Kayttaja", user.getName());
        assertEquals("testaaja", user.getUsername());
    }
    
    @Test
    public void nonExistingUserIsFound() {
        User user = dao.findByUsername("eiole");
        assertEquals(null, user);
    }
  
    @Test
    public void savedUserIsFound() throws Exception {
        User nawUser = new User("aleksi", "aleksi testi");
        dao.create(nawUser);
        
        User user = dao.findByUsername("aleksi");
        assertEquals("aleksi testi", user.getName());
        assertEquals("aleksi", user.getUsername());
    }
    
    @After
    public void tearDown() {
        userFile.delete();
    }
}