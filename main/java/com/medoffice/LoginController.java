//controllers  intercepts user inputs , coordinates data processing via the Model, and selects the correct View to display back to the user
package com.medoffice;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;
    
    @FXML
    private Label statusLabel;

    @FXML
    private void handleLogin(){
        
        String username=usernameField.getText();
        String password=passwordField.getText();
        com.medoffice.dao.UserDAO user =new com.medoffice.dao.UserDAO();
        var isValid =user.validateLogin(username, password);
        if (isValid){
            statusLabel.setText("Login successful");
            PauseTransition pause=new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("dashboard.fxml"));
                Parent root;
                try {
                    root=loader.load();
                    DashboardController controller=loader.getController();
                    controller.setStage(this.stage);
                    
                    Scene scene =new Scene(root,400,300);
                    
                    this.stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                });
            pause.play();
            

        }else{
            statusLabel.setText("invalid username or password");
        }

    }
    @FXML
    private void handleRegister(){
        var username=usernameField.getText();
        var password=passwordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Username and password cannot be empty");
            return;
        }
        com.medoffice.dao.UserDAO user =new com.medoffice.dao.UserDAO();
        var isValid=user.addUser(username, password);
        if (isValid){
           statusLabel.setText("register successfully");

        }else{
            statusLabel.setText("registration failed");
        }

        
    }
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    
    
}
