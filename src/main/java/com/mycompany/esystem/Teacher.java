/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.esystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ljamaiso
 */
public class Teacher extends MaisoEnrollmentSystem {
    int Tid;
    String Tname;
    String Tadd;
    String Tcontact;
    String Tdept;

    Teacher() {
        // Do not automatically connect to DB in constructor
        // Connection will be handled when needed
    }

    public void connectDB() {
        this.DBConnect(); // Only connect when explicitly called
    }

    public int SaveRecord(String name, String add, String contact, String dept) {
        int nextTid = -1;
        try {
            this.DBConnect();

            nextTid = 3001; // Default start ID for teachers
            try {
                String maxIdQuery = "SELECT MAX(Tid) FROM Teachers";
                ResultSet rs = st.executeQuery(maxIdQuery);
                if (rs.next()) {
                    int maxId = rs.getInt(1);
                    if (maxId > 0) { // If the table is not empty
                        nextTid = maxId + 1;
                    }
                    // Ensure the ID is at least 3001
                    if (nextTid < 3001) {
                        nextTid = 3001;
                    }
                }
            } catch (Exception ex) {
                System.out.println("Error getting next teacher ID: " + ex.getMessage());
            }

            String insertQuery = "INSERT INTO Teachers (Tid, Tname, Tadd, Tcontact, Tdept) VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement pst = con.prepareStatement(insertQuery);
            pst.setInt(1, nextTid);
            pst.setString(2, name);
            pst.setString(3, add);
            pst.setString(4, contact);
            pst.setString(5, dept);

            pst.executeUpdate();
            System.out.println("Insert success! New Teacher record created with ID: " + nextTid);

        } catch (Exception ex) {
            System.out.println("Failed to insert record: " + ex.getMessage());
        }
        return nextTid;
    }

    public void DeleteRecord(int Tid) {
        this.DBConnect();
        try {
            String query = "DELETE FROM Teachers WHERE Tid = ?";
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setInt(1, Tid);

            int result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Record deleted successfully!");
            } else {
                System.out.println("Failed to delete record. ID not found.");
            }
            this.con.close();
        } catch (Exception ex) {
            System.out.println("Delete FAILED: " + ex);
        }
    }

    public void UpdateRecord(int Tid, String Name, String Address, String Contact, String Dept) {
        this.DBConnect();

        String query = "UPDATE Teachers SET Tname = ?, Tadd = ?, Tcontact = ?, Tdept = ? WHERE Tid = ?";

        try {
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setString(1, Name);
            pst.setString(2, Address);
            pst.setString(3, Contact);
            pst.setString(4, Dept);
            pst.setInt(5, Tid);

            pst.executeUpdate();
            System.out.println("Information Updated Successfully!");
        } catch (Exception ex) {
            System.out.println("Failed to Update Information." + ex);
        }
    }

    public void LoadRecord() {
        System.out.print("Update Clicked");
    }
}