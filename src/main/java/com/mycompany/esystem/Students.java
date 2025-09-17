
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
public class Students extends MaisoEnrollmentSystem {
    int Studid;
    String name;
    String Address;
    String Contact;
    String Gender;
    String Yrlvl;
    
    
    Students(){
        // Do not automatically connect to DB in constructor
        // Connection will be handled when needed
    }
    
    public void connectDB(){
        this.DBConnect(); // Only connect when explicitly called
    }    
    
    
    public int SaveRecord(String name, String add, String contact, String gender, String yearLvl) {
        int nextStudid = -1;
        try {
            this.DBConnect();

            nextStudid = 1001; // Default start ID
            try {
                String maxIdQuery = "SELECT MAX(studid) FROM Students";
                ResultSet rs = st.executeQuery(maxIdQuery);
                if (rs.next()) {
                    int maxId = rs.getInt(1);
                    if (maxId > 0) { // If the table is not empty
                        nextStudid = maxId + 1;
                    }
                    // Ensure the ID is at least 1001
                    if (nextStudid < 1001) {
                        nextStudid = 1001;
                    }
                }
            } catch (Exception ex) {
                System.out.println("Error getting next student ID: " + ex.getMessage());
                // If there's an error, it will default to 1001, which is safe.
            }

            String insertQuery = "INSERT INTO Students (studid, studname, studadd, studcontact, studgender, yrlvl) VALUES (?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pst = con.prepareStatement(insertQuery);
            pst.setInt(1, nextStudid);
            pst.setString(2, name);
            pst.setString(3, add);
            pst.setString(4, contact);
            pst.setString(5, gender);
            pst.setString(6, yearLvl);

            pst.executeUpdate();
            System.out.println("Insert success! New Student record created with ID: " + nextStudid);

        } catch (Exception ex) {
            System.out.println("Failed to insert record: " + ex.getMessage());
        }
        return nextStudid;
    }
     
    
    public void DeleteRecord(int Studid){
        this.DBConnect();
        try {
            String query = "DELETE FROM Students WHERE studid = ?";
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setInt(1, Studid);

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
    
    public void UpdateRecord(int Studid, String Name, String Address, String Contact, String Gender, String Yrlvl){
        this.DBConnect();
        
        String query = "UPDATE Students SET studname = ?, studadd = ?, studcontact = ?, studgender = ?, yrlvl = ? WHERE studid = ?";
        
         try {
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setString(1, Name);
            pst.setString(2, Address);
            pst.setString(3, Contact);
            pst.setString(4, Gender);
            pst.setString(5, Yrlvl);
            pst.setInt(6, Studid);

            pst.executeUpdate();
            System.out.println("Information Updated Successfully!");
        } catch (Exception ex) {
            System.out.println("Failed to Update Information." + ex);
        }
    }
    
        
    public void LoadRecord(){
        System.out.print("Update Clicked");
    }
}
