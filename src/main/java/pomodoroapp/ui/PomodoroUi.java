/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodoroapp.ui;

import java.io.FileInputStream;
import java.util.Properties;
import javafx.animation.Timeline;
import pomodoroapp.domain.PomodoroService;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pomodoroapp.dao.FilePomodoroDao;
import pomodoroapp.dao.FileUserDao;

/**
 *
 * @author aleksipaavola
 */
public class PomodoroUi extends Application {
    
    private PomodoroService pomodoroService;
    
    private Scene pomodoroScene;
    private Scene newUserScene;
    private Scene loginScene;
    
    private VBox pomodoroNodes;
    private Label menuLabel = new Label();
    
    @Override
    public void init() throws Exception {
        
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));
        
        String userFile = properties.getProperty("userFile");
        String pomodoroFile = properties.getProperty("doneFile");
            
        FileUserDao userDao = new FileUserDao(userFile);
        FilePomodoroDao pomodoroDao = new FilePomodoroDao(pomodoroFile, userDao);
        pomodoroService = new PomodoroService(pomodoroDao, userDao);
    }
    
    @Override
    public void start(Stage primaryStage) {
        // login scene
        
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("username");
        TextField usernameInput = new TextField();
        
        inputPane.getChildren().addAll(loginLabel, usernameInput);
        Label loginMessage = new Label();
        
        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");
        loginButton.setOnAction(e->{
            String username = usernameInput.getText();
            menuLabel.setText(username + " logged in...");
            if ( pomodoroService.login(username) ){
                loginMessage.setText("");
                primaryStage.setScene(pomodoroScene);  
                usernameInput.setText("");
            } else {
                loginMessage.setText("use does not exist");
                loginMessage.setTextFill(Color.RED);
            }      
        });  
        
        createButton.setOnAction(e->{
            usernameInput.setText("");
            primaryStage.setScene(newUserScene);   
        });  
        
        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton, createButton);       
        
        loginScene = new Scene(loginPane, 300, 250);    
   
        // new createNewUserScene
        
        VBox newUserPane = new VBox(10);
        
        HBox newUsernamePane = new HBox(10);
        newUsernamePane.setPadding(new Insets(10));
        TextField newUsernameInput = new TextField(); 
        Label newUsernameLabel = new Label("username");
        newUsernameLabel.setPrefWidth(100);
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameInput);
     
        HBox newNamePane = new HBox(10);
        newNamePane.setPadding(new Insets(10));
        TextField newNameInput = new TextField();
        Label newNameLabel = new Label("name");
        newNameLabel.setPrefWidth(100);
        newNamePane.getChildren().addAll(newNameLabel, newNameInput);        
        
        Label userCreationMessage = new Label();
        
        Button createNewUserButton = new Button("create");
        createNewUserButton.setPadding(new Insets(10));

        createNewUserButton.setOnAction(e->{
            String username = newUsernameInput.getText();
            String name = newNameInput.getText();
   
            if ( username.length()==2 || name.length()<2 ) {
                userCreationMessage.setText("username or name too short");
                userCreationMessage.setTextFill(Color.RED);              
            } else if ( pomodoroService.createUser(username, name) ){
                userCreationMessage.setText("");                
                loginMessage.setText("new user created");
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(loginScene);      
            } else {
                userCreationMessage.setText("username has to be unique");
                userCreationMessage.setTextFill(Color.RED);        
            }
 
        });  
        
        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, createNewUserButton); 
       
        newUserScene = new Scene(newUserPane, 300, 250);
        
        // main scene
               
        BorderPane mainPane = new BorderPane();
        pomodoroScene = new Scene(mainPane, 300, 250);
        
        HBox menuPane = new HBox(10);    
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button logoutButton = new Button("logout");      
        menuPane.getChildren().addAll(menuLabel, menuSpacer, logoutButton);
        logoutButton.setOnAction(e->{
            pomodoroService.logout();
            primaryStage.setScene(loginScene);
        });  
        
        Integer STARTTIME = 15;
        Timeline timeline = new Timeline();
        Label timerLabel = new Label();
        IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
        
        
        Button startButton = new Button("Start timer");
        startButton.setOnAction(e-> {
          pomodoroService.createPomodoro(STARTTIME,timeline, timerLabel, timeSeconds);
        });
       
        
        HBox createForm = new HBox(10);   
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        createForm.getChildren().addAll(spacer, timerLabel, startButton);
        
        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 4em;");

        pomodoroNodes = new VBox(10);
        pomodoroNodes.setMaxWidth(280);
        pomodoroNodes.setMinWidth(280);
        
        mainPane.setBottom(createForm);
        mainPane.setTop(menuPane);
        mainPane.setCenter(timerLabel);
       
        primaryStage.setTitle("Pomodoro App");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e->{
            System.out.println("closing");
            System.out.println(pomodoroService.getLoggedUser());
            if (pomodoroService.getLoggedUser()!=null) {
                e.consume();   
            }
            
        });
    }
    @Override
    public void stop() {
      System.out.println("App is closing");
    }  
    
    public static void main(String[] args) {
        launch(args);
    }

}
   
