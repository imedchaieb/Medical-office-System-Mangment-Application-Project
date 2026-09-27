package com.medoffice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medoffice.model.Patient;
import com.medoffice.util.DatabaseConnection;

public class PatientDAO {
    public void addPatient(Patient patient){
        String sql="INSERT INTO patients (first_name, last_name,phone,address, date_of_birth) values (?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, patient.getFirstName());
        stmt.setString(2, patient.getLastName());
        stmt.setString(3, patient.getPhone());
        stmt.setString(4, patient.getAddress());
        stmt.setString(5, patient.getDateOfBirth());

        stmt.executeUpdate();//return number of rows affected

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  List<Patient> getAllPatients(){
        List<Patient> patients =new ArrayList<>();
        String sql ="SELECT * FROM patients";
        try(Connection conn =DatabaseConnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql);
            ResultSet res= stmt.executeQuery();) {//that called try with ressources , all three get auto-closed when the block ends
                while (res.next()){
                    int id=res.getInt("id");
                    String firstName=res.getString("first_name");
                    String lastName=res.getString("last_name");
                    String phone=res.getString("phone");
                    String address=res.getString("address");
                    String dateOfBirth=res.getString("date_of_birth");

                    Patient p=new Patient(id,firstName,lastName,phone,address,dateOfBirth);
                    patients.add(p);



                }

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;

    }
    public void updatePatient(Patient p){
        String sql="UPDATE patients\n" + //
                        "SET first_name = ?, last_name = ?, phone = ?, address = ?, date_of_birth = ?\n" + //
                        "WHERE id = ?";
        try(Connection conn=DatabaseConnection.getConnection();
            PreparedStatement stmt =conn.prepareStatement(sql);
            ) {
                stmt.setString(1,p.getFirstName());
                stmt.setString(2,p.getLastName());
                stmt.setString(3,p.getPhone());
                stmt.setString(4,p.getAddress());
                stmt.setString(5,p.getDateOfBirth());
                stmt.setInt(6, p.getId());

                stmt.executeUpdate();
  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePatient(int id){
        String sql="DELETE FROM patients WHERE id=?";
        try (Connection conn=DatabaseConnection.getConnection();
            PreparedStatement stmt =conn.prepareStatement(sql);
            ) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    
}
