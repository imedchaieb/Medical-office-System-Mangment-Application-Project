package com.medoffice;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    @Override
    public void init() {//create the tables before the application screen appears
     com.medoffice.util.DatabaseInitializer.initialize();
    }

    @Override
    public void start(Stage primaryStage){// primarystage is the main window of the application
        FXMLLoader loader= new FXMLLoader(getClass().getResource("login.fxml"));//FXMLLoader class responsable for reading .fxml file and turning it into actual javafx ui objects
        Parent root ;//is a general JavaFX type that covers any UI container (VBox, StackPane, etc)
        
        try {
            root = loader.load();//actually reads the file, builds all the UI nodes, creates a LoginController instance, and injects your @FXML fields
            LoginController controller = loader.getController();//it returns the actual LoginController instance that FXMLLoader created while loading the FXML (remember, fx:controller told it which class to instantiate
            controller.setStage(primaryStage);// that opeation called dependency injection by setter
            
            Scene scene=new Scene(root,400,300);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Medical Office System");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
   
    public static void main(String[] args) {
        launch(args);

    }
}

    

