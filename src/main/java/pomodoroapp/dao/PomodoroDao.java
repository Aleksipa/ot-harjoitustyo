/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodoroapp.dao;

/**
 *
 * @author aleksipaavola
 */
import java.util.List;
import pomodoroapp.domain.Pomodoro;

public interface PomodoroDao {

    Pomodoro create(Pomodoro pomodoro) throws Exception;


}