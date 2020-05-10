package pomodoroapp.dao;

/**
 *
 * @author aleksipaavola
 */
import java.util.List;
import pomodoroapp.domain.Pomodoro;

public interface PomodoroDao {

    Pomodoro createOrUpdate(Pomodoro pomodoro) throws Exception;
    
    List<Pomodoro> getAll();


}