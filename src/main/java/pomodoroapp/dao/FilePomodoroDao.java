package pomodoroapp.dao;

/**
 *
 * @author aleksipaavola
 */
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;
import pomodoroapp.domain.Pomodoro;
import pomodoroapp.domain.User;

public class FilePomodoroDao implements PomodoroDao {
    private String file;
    public List<Pomodoro> pomodoros;
    private List<Pomodoro> newPomodoros;

    public FilePomodoroDao(String file, UserDao users) throws Exception {
        pomodoros = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                int count = Integer.parseInt(parts[0]);
                User user = users.getAll().stream().filter(u->u.getUsername().equals(parts[1])).findFirst().orElse(null); 
                Pomodoro pomodoro = new Pomodoro(count, user);
                pomodoros.add(pomodoro);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }
    
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Pomodoro pomodoro : pomodoros) {
                writer.write(pomodoro.getCount() + ";" + pomodoro.getUser().getUsername() + "\n");
            }
        }
    }
    
    private int generateId() {
        return pomodoros.size() + 1;
    }
    
    @Override
    public List getAll() {
        return pomodoros;
    }
    
    @Override
    public Pomodoro createOrUpdate(Pomodoro pomodoro) throws Exception {
        newPomodoros = new ArrayList<>();
        boolean found = false;
        if (pomodoros.isEmpty()) {
            pomodoros.add(pomodoro);
        } else {
            for (Pomodoro p : pomodoros) {
                if (p.getUser().getUsername() == pomodoro.getUser().getUsername()) {
                    p.setCount(p.getCount() + 1);
                    found = true;
                } 
            }
            if (found == false) {
                newPomodoros.add(pomodoro);
            }
        }
        pomodoros.addAll(newPomodoros);
        save();
        return pomodoro;
    }   
}