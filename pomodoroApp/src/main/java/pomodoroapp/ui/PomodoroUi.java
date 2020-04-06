/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodoroapp.ui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author aleksipaavola
 */
public class PomodoroUi extends Application {
    
    @Override
    public void start(Stage ikkuna) {
        
        Button lisaaNappi = new Button("Aloita");
         
        GridPane komponenttiryhma = new GridPane();
         
        komponenttiryhma.add(lisaaNappi, 1, 2);
        
        komponenttiryhma.setHgap(10);
        komponenttiryhma.setVgap(10);
        komponenttiryhma.setPadding(new Insets(40, 100, 40, 100));
        
        Scene nakyma = new Scene(komponenttiryhma);
        
        ikkuna.setScene(nakyma);
        ikkuna.setTitle("Pomodoro App");
        ikkuna.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
   
