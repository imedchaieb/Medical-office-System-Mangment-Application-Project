package com.medoffice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.medoffice.model.Appointment;
import com.medoffice.util.DatabaseConnection;

public class AppointmentDAO {
    public void addAppointment (Appointment app){
        String sql="insert into appointments (patient_id,date,time) values (?,?,?) ";
        try(Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, app.getPatientId());
                stmt.setString(2, app.getDate());
                stmt.setString(3, app.getTime());

                stmt.executeUpdate();


            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateAppointment(Appointment app){
        String sql="update appointments set patient_id=?, date=?,time=? where id=? ";
          try(Connection conn=DatabaseConnection.getConnection();
            PreparedStatement stmt =conn.prepareStatement(sql);
            ) {
                stmt.setInt(1,app.getPatientId());
                stmt.setString(2,app.getDate());
                stmt.setString(3,app.getTime());
                stmt.setInt(4,app.getId());
              

                stmt.executeUpdate();
  
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void deleteAppointment(int id){
         String sql="DELETE FROM appointments WHERE id=?";
        try (Connection conn=DatabaseConnection.getConnection();
            PreparedStatement stmt =conn.prepareStatement(sql);
            ) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

     public  List<Appointment> getAllAppointments(){
        List<Appointment> appointments =new ArrayList<>();
        String sql ="SELECT appointments.id, appointments.patient_id, appointments.date, appointments.time, "
               + "patients.first_name, patients.last_name "
               + "FROM appointments "
               + "JOIN patients ON appointments.patient_id = patients.id";;
        try(Connection conn =DatabaseConnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql);
            ResultSet res= stmt.executeQuery();) {//that called try with ressources , all three get auto-closed when the block ends
                while (res.next()){
                    int id=res.getInt("id");
                    int patientId=res.getInt("patient_id");
                    String date=res.getString("date");
                    String time=res.getString("time");
                    String firstName=res.getString("first_name");
                    String lastName=res.getString("last_name");
                  

                    Appointment app=new Appointment(id,patientId,date,time,firstName,lastName);
                    appointments.add(app);

                }

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appointments;

    }
    
}
