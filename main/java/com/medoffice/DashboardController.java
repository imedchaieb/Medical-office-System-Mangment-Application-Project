package com.medoffice;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashboardController {
    @FXML
    private Button addPatientButton;
    @FXML
    private Button viewPatientsButton;
    

@FXML
private void handleAddPatient() {
    Scene currScene=stage.getScene();
    FXMLLoader loader= new FXMLLoader(getClass().getResource("addPatient.fxml"));//FXMLLoader class responsable for reading .fxml file and turning it into actual javafx ui objects 
    try {
        Parent root=loader.load();
        AddPatientController controller =loader.getController();
        controller.setStage(this.stage);
        controller.setPreviousScene(currScene);
        Scene scene =new Scene(root,400,350);
        
        this.stage.setScene(scene);  
    } catch ( IOException e) {
        e.printStackTrace();
    }
    
}

@FXML
private void handleViewPatients() {
    Scene currScene=stage.getScene();
    FXMLLoader loader= new FXMLLoader(getClass().getResource("viewPatients.fxml"));//FXMLLoader class responsable for reading .fxml file and turning it into actual javafx ui objects 
    try {
        Parent root=loader.load();
        ViewPatientsController controller =loader.getController();
        controller.setStage(this.stage);
        controller.setPreviousScene(currScene);
        Scene scene =new Scene(root,400,300);
        this.stage.setScene(scene);  
    } catch ( IOException e) {
        e.printStackTrace();
    }
}
private Stage stage;

public void setStage(Stage stage) {
        this.stage = stage;
    }
@FXML 
private void handleAppointments(){
    Scene currScene=stage.getScene();
    FXMLLoader loader= new FXMLLoader(getClass().getResource("appointments.fxml"));//FXMLLoader class responsable for reading .fxml file and turning it into actual javafx ui objects 
    try {
        Parent root=loader.load();
        AppointmentsController controller =loader.getController();
        controller.setStage(this.stage);
        controller.setPreviousScene(currScene);
        Scene scene =new Scene(root,600,300);
        this.stage.setScene(scene);  
    } catch ( IOException e) {
        e.printStackTrace();
    }

}
    

    
}
