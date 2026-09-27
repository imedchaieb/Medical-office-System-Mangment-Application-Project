package com.medoffice.util;

import java.sql.Connection;
import java.sql.Statement;


public class DatabaseInitializer{
    public static void initialize(){
        try(Connection conn =DatabaseConnection.getConnection();
        Statement stmt=conn.createStatement()) {
            stmt.execute("create table if not exists users(\r\n" + //
                                "    id integer primary key autoincrement,\r\n" + //
                                "    username text not null unique,\r\n" + //
                                "    password text not null \r\n" + //
                                ");"
                        );

            stmt.execute("create table if not exists patients(\r\n" + //
                                "    id integer primary key autoincrement,\r\n" + //
                                "    first_name text not null,\r\n" + //
                                "    last_name text not null,\r\n" + //
                                "    phone text , --because not all patients should have a sim card \r\n" + //
                                "    address text not null,\r\n" + //
                                "    date_of_birth text not null\r\n" + //
                                "\r\n" + //
                                ");"
                        );

            stmt.execute("create table if not exists appointments(\r\n" + //
                                "    id integer primary key autoincrement,\r\n" + //
                                "    patient_id  integer not null,\r\n" + //
                                "    date text not null,\r\n" + //
                                "    time text not null,\r\n" + //
                                "    foreign key (patient_id) references patients(id)\r\n" + //
                                ");"
                        );

            
        }
         catch (Exception e) {
            e.printStackTrace();
        }
    }

}