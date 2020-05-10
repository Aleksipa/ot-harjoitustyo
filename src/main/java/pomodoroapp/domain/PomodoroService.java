package pomodoroapp.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pomodoroapp.dao.PomodoroDao;
import pomodoroapp.dao.UserDao;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    /**
    * Suoritetun pomodoron kirjaaminen kirjautuneena olevalle käyttäjälle
    *
    * @param   content   luotavan pomodoron sisältö
    */
    
    public boolean completePomodoro(Pomodoro pomodoro) {
        try {   
            pomodoroDao.createOrUpdate(pomodoro);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    /**
    * kirjautuneen käyttäjän suorittamat pomodorot
    * 
    * @return kirjautuneen käyttäjän suorittamat pomodorot
    */
    
    public int getPomodoroCount() {
        if (loggedIn == null) {
            return 0;
        }
          
        List<Pomodoro> result = pomodoroDao.getAll()
            .stream()
            .filter(p-> p.getUser().equals(loggedIn))
            .collect(Collectors.toList());
        
        if (result.isEmpty()) {
            return 0;       
        } else {
            return result.get(0).getCount();
        }
    }
    
    /**
    * sisäänkirjautuminen
    * 
    * @param username käyttäjätunnus
    * 
    * @return true jos käyttäjätunnus on olemassa, muuten false 
    */  
    
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
        } catch (Exception e) {
            
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

}

