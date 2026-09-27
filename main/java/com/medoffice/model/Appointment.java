package com.medoffice.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final IntegerProperty patientId = new SimpleIntegerProperty();
    private final StringProperty date = new SimpleStringProperty();
    private final StringProperty time = new SimpleStringProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();

    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }
    public IntegerProperty idProperty() { return id; }

    public int getPatientId() { return patientId.get(); }
    public void setPatientId(int patientId) { this.patientId.set(patientId); }
    public IntegerProperty patientIdProperty() { return patientId; }

    public String getDate() { return date.get(); }
    public void setDate(String date) { this.date.set(date); }
    public StringProperty dateProperty() { return date; }

    public String getTime() { return time.get(); }
    public void setTime(String time) { this.time.set(time); }
    public StringProperty timeProperty() { return time; }

    public String getFirstName() { return firstName.get(); }
    public void setFirstName(String firstName) { this.firstName.set(firstName); }
    public StringProperty firstNameProperty() { return firstName; }

    public String getLastName() { return lastName.get(); }
    public void setLastName(String lastName) { this.lastName.set(lastName); }
    public StringProperty lastNameProperty() { return lastName; }

    // constructeur existant (Add/Update)
    public Appointment() {}

    public Appointment(int id, int patientId, String date, String time) {
        setId(id);
        setPatientId(patientId);
        setDate(date);
        setTime(time);
    }

    // nouveau constructeur (pour getAllAppointments avec JOIN)
    public Appointment(int id, int patientId, String date, String time, String firstName, String lastName) {
        this(id, patientId, date, time);
        setFirstName(firstName);
        setLastName(lastName);
    }
}