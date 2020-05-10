/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomodoroapp.ui;

import java.io.FileInputStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import pomodoroapp.domain.PomodoroService;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import pomodoroapp.dao.FilePomodoroDao;
import pomodoroapp.dao.FileUserDao;
import pomodoroapp.domain.Pomodoro;

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
    
    private Timeline timeLine;
    public static StringProperty displayTime = new SimpleStringProperty("1:00");
    public static IntegerProperty roundsCompleted = new SimpleIntegerProperty(0);
    
    @Override
    public void init() throws Exception {
        
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));
        
        String userFile = properties.getProperty("userFile");
        String pomodoroFile = properties.getProperty("pomodoroFile");
            
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
        Label pomodoroCount = new Label();
        Label pomodoroCountText = new Label();
        
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
                roundsCompleted.setValue(pomodoroService.getPomodoroCount());
                pomodoroCount.textProperty().bind(roundsCompleted.asString());
                usernameInput.setText("");
            } else {
                loginMessage.setText("user does not exist");
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
       
        newUserScene = new Scene(newUserPane, 350, 250);
        
        // main scene
               
        BorderPane mainPane = new BorderPane();
        pomodoroScene = new Scene(mainPane, 350, 250);
        
        HBox menuPane = new HBox(10);    
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button logoutButton = new Button("logout");      
        menuPane.getChildren().addAll(menuLabel, menuSpacer, logoutButton);
        logoutButton.setOnAction(e->{
            pomodoroService.logout();
            primaryStage.setScene(loginScene);
        });
        
        Label timerLabel = new Label();
        
        // Start pomodoro timer
        Button startButton = new Button("Start timer");
        startButton.setOnAction(e-> {
            if (timeLine == null || timeLine.getStatus().equals(Status.STOPPED)) {
                startTimer();
            } else {
                switch (timeLine.getStatus()) {
                    case PAUSED:
                        continueTimer();
                        break;
                    default:
                        break;
                }
            }
        });
        
        // Pause pomodoro timer
        Button pauseButton = new Button("Pause timer");
        pauseButton.setOnAction(e-> {
            if ((timeLine == null || timeLine.getStatus().equals(Status.STOPPED))) {
                startTimer();
            } else {
                switch (timeLine.getStatus()) {
                    case PAUSED:
                        continueTimer();
                        break;
                    default:
                        pauseTimer();
                        break;
                }
            }
        });
        
        HBox createForm = new HBox(10);   
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        pomodoroCountText.setText("Rounds completed:");
        
        createForm.getChildren().addAll(pomodoroCountText, pomodoroCount, spacer, timerLabel, pauseButton, startButton);
        
        timerLabel.textProperty().bind(displayTime);
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 4em;");
        
        pomodoroCountText.setStyle("-fx-font-size: 1.3em;");
        pomodoroCount.setStyle("-fx-font-size: 1.3em;");

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
    
    private void startTimer() {
    Pomodoro pomodoro = new Pomodoro(0, pomodoroService.getLoggedUser());
    pomodoro.setUser(pomodoroService.getLoggedUser());
    pomodoro.setTime(LocalTime.of(0, 1));
    displayTime.set(pomodoro.getTime().format(DateTimeFormatter.ofPattern("mm:ss")));
    timeLine = new Timeline();
    timeLine.setCycleCount(pomodoro.getTimeInSeconds());
    timeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {
            pomodoro.setTime(pomodoro.getTime().minusSeconds(1));
            displayTime.set(pomodoro.getTime().format(DateTimeFormatter.ofPattern("mm:ss")));                                
            if ("00:00".equals(pomodoro.getTime().format(DateTimeFormatter.ofPattern("mm:ss")))){
                alert("It's time for a break", "You completed your pomodoro!");
                pomodoro.addCount();
                pomodoroService.completePomodoro(pomodoro);
                roundsCompleted.setValue(pomodoroService.getPomodoroCount());
                resetTimer(pomodoro);
            }
        }
    }));
    timeLine.playFromStart();
    }
    
    public void resetTimer(Pomodoro pomodoro) {
        if (timeLine != null) {
            timeLine.stop();
        }
        displayTime.set("10:00");
        pomodoro.initializeTime();
    }
    
    public void pauseTimer() {
        boolean paused = false;
        if (timeLine != null && timeLine.getStatus().equals(Status.RUNNING)) {
            timeLine.pause();
            paused = true;
        }
    }
    
    public void continueTimer() {
        timeLine.play();
    }
    
    public void alert(String title, String message) {
        Stage window = new Stage();
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close this window");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
    
    @Override
    public void stop() {
      System.out.println("App is closing");
    }  
    
    public static void main(String[] args) {
        launch(args);
    }

}
   
