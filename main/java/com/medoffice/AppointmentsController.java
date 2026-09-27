package com.medoffice;

import java.io.IOException;

import com.medoffice.dao.AppointmentDAO;
import com.medoffice.model.Appointment;

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

public class AppointmentsController {
    @FXML
    private TableView<Appointment> appointmentsTable;

    @FXML
    private TableColumn<Appointment, Integer> idColumn;
    @FXML
    private TableColumn<Appointment, Integer> patientIdColumn;
    @FXML
    private TableColumn<Appointment, String> dateColumn;
    @FXML
    private TableColumn<Appointment, String> timeColumn;
    @FXML
    private TableColumn<Appointment, String> firstNameColumn;
    @FXML
    private TableColumn<Appointment, String> lastNameColumn;
 

    
    private Stage stage;
    private Scene previouScene;


public void setStage(Stage stage) {
        this.stage = stage;
    }

public void setPreviousScene(Scene scene) {
        this.previouScene = scene;
    }

@FXML
private void handleBack(){
        stage.setScene(previouScene);
    }

@FXML
public void initialize(){//runs automatically after a bunch of sequences: read .fxml , creat the controller , create the UI objects and injects thos UI into @FXML fields 
    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    patientIdColumn.setCellValueFactory(new PropertyValueFactory<>("patientId"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
    firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    AppointmentDAO appointmentsDAO =new AppointmentDAO();
    ObservableList<Appointment> data = FXCollections.observableArrayList(appointmentsDAO.getAllAppointments());
    appointmentsTable.setItems(data);

}
@FXML
private void handleAdd() {
  Scene currScene=stage.getScene();
 FXMLLoader loader= new FXMLLoader(getClass().getResource("addAppointment.fxml"));
    try {
        Parent root=loader.load();
        AddAppointmentController controller =loader.getController();
        controller.setStage(this.stage);
        controller.setPreviousScene(currScene);
        Scene scene =new Scene(root,400,350);
        
        this.stage.setScene(scene);  
    } catch ( IOException e) {
        e.printStackTrace();
    }
}

@FXML
private void handleUpdate() {
    Appointment selected = appointmentsTable.getSelectionModel().getSelectedItem();

    if (selected == null) {
        System.out.println("please select an appointment first");
        return;
    }
    Scene currScene=stage.getScene();
    FXMLLoader loader=new FXMLLoader(getClass().getResource("updateAppointment.fxml"));
    Parent root;
                try {
                    root=loader.load();
                    updateAppointmentController controller=loader.getController();
                    controller.setStage(this.stage);
                    controller.setPreviousScene(currScene);
                    controller.setAppointment(selected);
                    Scene scene =new Scene(root,400,300);
                    this.stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
}

@FXML
private void handleDelete() {
  Appointment selected = appointmentsTable.getSelectionModel().getSelectedItem();
    if (selected ==null){
        System.out.println("please  select an appointment first");
        return;
    }
    AppointmentDAO appdao =new AppointmentDAO();
    appdao.deleteAppointment(selected.getId());
    //refraire tableview
    ObservableList<Appointment> data = FXCollections.observableArrayList(appdao.getAllAppointments());
    appointmentsTable.setItems(data);
}
    
}
