package com.medoffice;

import com.medoffice.dao.PatientDAO;
import com.medoffice.model.Patient;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPatientController {
    @FXML
    private TextField firstNameField;

    @FXML 
    private TextField lastNameField;

    @FXML 
    private TextField phoneField;

    @FXML 
    private TextField addressField;

    @FXML
    private TextField dateOfBirthField;

    @FXML
    private Label statusLabel;

    private Stage stage;
    private Scene previouScene;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setPreviousScene (Scene scene){
        this.previouScene=scene;
    }

    @FXML
    private void handleSave(){
        var firstname= firstNameField.getText();
        var lastname =lastNameField.getText();
        var phone=phoneField.getText();
        var address=addressField.getText();
        var dateofbirth=dateOfBirthField.getText();
        if (firstname.isEmpty() || lastname.isEmpty() || phone.isEmpty() || address.isEmpty() ||dateofbirth.isEmpty()){
            statusLabel.setText("you must fill all the fields");
            return;
        }
        Patient patient=new Patient(0,firstname,lastname, phone,address,dateofbirth);
        PatientDAO patientdao=new PatientDAO();
        patientdao.addPatient(patient);
        statusLabel.setText("patient added successfully");
    } 

    @FXML
    private void handleBack(){
        stage.setScene(previouScene);
    }




    
}
