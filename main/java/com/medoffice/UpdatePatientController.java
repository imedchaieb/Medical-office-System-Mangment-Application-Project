package com.medoffice;

import com.medoffice.dao.PatientDAO;
import com.medoffice.model.Patient;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdatePatientController {
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

private Stage stage ;
private Scene previouScene;
private Patient patient;

public void setStage(Stage stage) {
        this.stage = stage;
}

public void setPreviousScene(Scene scene) {
        this.previouScene = scene;
    }

public void setPatient(Patient selected){
    this.patient=selected;

    firstNameField.setText(patient.getFirstName());
    lastNameField.setText(patient.getLastName());
    phoneField.setText(patient.getPhone());
    addressField.setText(patient.getAddress());
    dateOfBirthField.setText(patient.getDateOfBirth());

}
@FXML
private void handleBack(){
    stage.setScene(previouScene);
}

@FXML
private void handleSave(){
        var firstname = firstNameField.getText();
        var lastname = lastNameField.getText();
        var phone = phoneField.getText();
        var address = addressField.getText();
        var dateofbirth = dateOfBirthField.getText();
         if (firstname.isEmpty() || lastname.isEmpty() || phone.isEmpty() || address.isEmpty() || dateofbirth.isEmpty()) {
            System.out.println("un champs qui manque");
            return;
        }
        var id =patient.getId();
        Patient currpatient=new Patient(id,firstname,lastname,phone,address,dateofbirth);
        PatientDAO pdao= new PatientDAO();
        pdao.updatePatient(currpatient);

        
       
    } 


    
}
