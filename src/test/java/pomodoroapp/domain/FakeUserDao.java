/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodoroapp.domain;

import java.util.ArrayList;
import java.util.List;
import pomodoroapp.dao.UserDao;

/**
 *
 * @author aleksipaavola
 */
public class FakeUserDao implements UserDao {
    List<User> users = new ArrayList<>();

    public FakeUserDao() {
        users.add(new User("testaaja", "Testi Kayttaja"));
    }
    
    @Override
    public User findByUsername(String username) {
        return users.stream().filter(u->u.getUsername().equals(username)).findFirst().orElse(null);
    }
    
    @Override
    public User create(User user) {
        users.add(user);
        return user;
    } 
    
    @Override
    public List<User> getAll() {
        return users;
    }

}
