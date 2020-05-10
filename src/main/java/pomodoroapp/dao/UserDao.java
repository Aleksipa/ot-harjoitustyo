package pomodoroapp.dao;

/**
 *
 * @author aleksipaavola
 */
import java.util.List;
import pomodoroapp.domain.User;

public interface UserDao {

    User create(User user) throws Exception;

    User findByUsername(String username);

    List<User> getAll();

}

