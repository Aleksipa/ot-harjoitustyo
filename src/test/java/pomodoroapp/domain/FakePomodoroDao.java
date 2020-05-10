package pomodoroapp.domain;

import java.util.ArrayList;
import java.util.List;
import pomodoroapp.dao.PomodoroDao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleksipaavola
 */
public class FakePomodoroDao implements PomodoroDao{
    List<Pomodoro> pomodoros;
    
    public FakePomodoroDao() {
        pomodoros = new ArrayList<>();
    }  
    
    @Override
    public Pomodoro createOrUpdate(Pomodoro pomodoro) {
        pomodoro.setCount(1);
        pomodoros.add(pomodoro);
        return pomodoro;
    }

    @Override
    public List<Pomodoro> getAll() {
        return pomodoros;
    }
 

}
