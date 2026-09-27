package com.medoffice;

import com.medoffice.dao.AppointmentDAO;
import com.medoffice.dao.PatientDAO;
import com.medoffice.model.Appointment;
import com.medoffice.model.Patient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class AddAppointmentController {
    @FXML
    private ComboBox<Patient> patientComboBox;

    @FXML
    private TextField dateField;

    @FXML
    private TextField timeField;

    @FXML
    private Label statusLabel;

    private Stage stage;
    private Scene previousScene;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setPreviousScene(Scene scene) {
        this.previousScene = scene;
    }

    @FXML 
    public void initialize(){
        PatientDAO patientdao=new PatientDAO();
        ObservableList<Patient>patients=FXCollections.observableArrayList(patientdao.getAllPatients());
        patientComboBox.setItems(patients);

        patientComboBox.setConverter(new StringConverter<Patient>(){
            @Override
            public String toString(Patient p){
                if(p==null){
                    return "";
                }
                return p.getFirstName()+" "+p.getLastName();
            }

            @Override 
            public Patient fromString(String string){
                return null;
            }
        });
    }
    @FXML
    public void handleSave(){
        var patient=patientComboBox.getValue();
        var date=dateField.getText();
        var time=timeField.getText();
         if (patient==null || date.isEmpty() || time.isEmpty() ){
            statusLabel.setText("you must fill all the fields");
            return;
        }
        Appointment app=new Appointment(0,patient.getId(),date,time);
        AppointmentDAO appdao=new AppointmentDAO();
        appdao.addAppointment(app);
        statusLabel.setText("appointment added successfully");

    }
    @FXML
    public void handleBack(){
        stage.setScene(previousScene);

    }
}
