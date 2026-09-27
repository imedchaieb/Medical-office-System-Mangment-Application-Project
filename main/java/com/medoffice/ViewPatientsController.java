package com.medoffice;

import java.io.IOException;

import com.medoffice.dao.PatientDAO;
import com.medoffice.model.Patient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ViewPatientsController {
@FXML
private TableView<Patient> patientsTable;
@FXML
private TableColumn<Patient, Integer> idColumn;
@FXML
private TableColumn<Patient,String> firstNameColumn;
@FXML
private TableColumn<Patient, String> lastNameColumn;
@FXML
private TableColumn<Patient, String> phoneColumn;
@FXML
private TableColumn<Patient, String> addressColumn;
@FXML
private TableColumn<Patient, String> dateOfBirthColumn;

private Stage stage ;
private Scene previousScene;

public void setStage(Stage stage) {
        this.stage = stage;
}

public void setPreviousScene(Scene scene) {
        this.previousScene = scene;
    }

@FXML
public void initialize(){//runs automatically after a bunch of sequences: read .fxml , creat the controller , create the UI objects and injects thos UI into @FXML fields 
    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
    addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
    dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
    PatientDAO patientDAO =new PatientDAO();
    ObservableList<Patient> data = FXCollections.observableArrayList(patientDAO.getAllPatients());
    patientsTable.setItems(data);

}

@FXML
private void handleBack() {
    stage.setScene(previousScene);
    }

@FXML
private void handleUpdateSelected(){
    Patient selected = patientsTable.getSelectionModel().getSelectedItem();
    if (selected ==null){
        System.out.println("please  select a patient first");
        return;
    }
    Scene currScene=stage.getScene();
    FXMLLoader loader=new FXMLLoader(getClass().getResource("updatePatient.fxml"));
    Parent root;
                try {
                    root=loader.load();
                    UpdatePatientController controller=loader.getController();
                    controller.setStage(this.stage);
                    controller.setPreviousScene(currScene);
                    controller.setPatient(selected);
                    Scene scene =new Scene(root,400,400);
                    
                    this.stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }


}
@FXML
private void handleDeletePatient(){
    Patient selected = patientsTable.getSelectionModel().getSelectedItem();
    if (selected ==null){
        System.out.println("please  select a patient first");
        return;
    }
    PatientDAO pdao =new PatientDAO();
    pdao.deletePatient(selected.getId());
    //refraire tableview
    ObservableList<Patient> data = FXCollections.observableArrayList(pdao.getAllPatients());
    patientsTable.setItems(data);

}


    
}
