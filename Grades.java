/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.esystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author lancejaspera.maiso
 */
public class Grades extends MaisoEnrollmentSystem {
    
    private int gradeId;
    private int eid;
    private String prelim;
    private String midterm;
    private String preFinal;
    private String finals;
    
    public Grades() {
        // Constructor
    }
    
    /**
     * Insert a new grade record for an enrollment
     * @param eid Enrollment ID
     * @param prelim Prelim grade
     * @param midterm Midterm grade
     * @param preFinal PreFinal grade
     * @param finals Final grade
     */
    public void SaveRecord(int eid, String prelim, String midterm, String preFinal, String finals) {
        MaisoEnrollmentSystem.DBConnect();
        try {
            // Check if grade already exists for this enrollment
            String checkQuery = "SELECT GradeID FROM Grades WHERE eid = ?";
            PreparedStatement checkPst = MaisoEnrollmentSystem.con.prepareStatement(checkQuery);
            checkPst.setInt(1, eid);
            ResultSet rs = checkPst.executeQuery();
            
            if (rs.next()) {
                System.out.println("Grade record already exists for this enrollment. Use UpdateRecord instead.");
                return;
            }
            
            // Insert new grade record
            String insertQuery = "INSERT INTO Grades (eid, Prelim, Midterm, PreFinal, Final) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = MaisoEnrollmentSystem.con.prepareStatement(insertQuery);
            pst.setInt(1, eid);
            pst.setString(2, prelim);
            pst.setString(3, midterm);
            pst.setString(4, preFinal);
            pst.setString(5, finals);
            
            pst.executeUpdate();
            System.out.println("Grade record inserted successfully for enrollment ID: " + eid);
        } catch (Exception ex) {
            System.out.println("Failed to insert grade record: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Update an existing grade record by enrollment ID
     * @param eid Enrollment ID
     * @param prelim Prelim grade
     * @param midterm Midterm grade
     * @param preFinal PreFinal grade
     * @param finals Final grade
     */
    public void UpdateRecord(int eid, String prelim, String midterm, String preFinal, String finals) {
        MaisoEnrollmentSystem.DBConnect();
        try {
            String updateQuery = "UPDATE Grades SET Prelim = ?, Midterm = ?, PreFinal = ?, Final = ? WHERE eid = ?";
            PreparedStatement pst = MaisoEnrollmentSystem.con.prepareStatement(updateQuery);
            pst.setString(1, prelim);
            pst.setString(2, midterm);
            pst.setString(3, preFinal);
            pst.setString(4, finals);
            pst.setInt(5, eid);
            
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Grade record updated successfully for enrollment ID: " + eid);
            } else {
                System.out.println("No grade record found for enrollment ID: " + eid);
            }
        } catch (Exception ex) {
            System.out.println("Failed to update grade record: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Update grades by student ID and subject ID
     * @param studId Student ID
     * @param subjId Subject ID
     * @param prelim Prelim grade
     * @param midterm Midterm grade
     * @param preFinal PreFinal grade
     * @param finals Final grade
     */
    public void UpdateRecordByStudentAndSubject(int studId, int subjId, String prelim, String midterm, String preFinal, String finals) {
        MaisoEnrollmentSystem.DBConnect();
        try {
            // First, get the enrollment ID
            String getEidQuery = "SELECT eid FROM Enroll WHERE studid = ? AND subjid = ?";
            PreparedStatement getEidPst = MaisoEnrollmentSystem.con.prepareStatement(getEidQuery);
            getEidPst.setInt(1, studId);
            getEidPst.setInt(2, subjId);
            ResultSet rs = getEidPst.executeQuery();
            
            if (rs.next()) {
                int eid = rs.getInt("eid");
                
                // Check if grade record exists
                String checkQuery = "SELECT GradeID FROM Grades WHERE eid = ?";
                PreparedStatement checkPst = MaisoEnrollmentSystem.con.prepareStatement(checkQuery);
                checkPst.setInt(1, eid);
                ResultSet checkRs = checkPst.executeQuery();
                
                if (checkRs.next()) {
                    // Update existing record
                    UpdateRecord(eid, prelim, midterm, preFinal, finals);
                } else {
                    // Insert new record
                    SaveRecord(eid, prelim, midterm, preFinal, finals);
                }
            } else {
                System.out.println("No enrollment found for Student ID: " + studId + " and Subject ID: " + subjId);
            }
        } catch (Exception ex) {
            System.out.println("Failed to update grade record: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Delete a grade record by enrollment ID
     * @param eid Enrollment ID
     */
    public void DeleteRecord(int eid) {
        MaisoEnrollmentSystem.DBConnect();
        try {
            String deleteQuery = "DELETE FROM Grades WHERE eid = ?";
            PreparedStatement pst = MaisoEnrollmentSystem.con.prepareStatement(deleteQuery);
            pst.setInt(1, eid);
            
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Grade record deleted successfully for enrollment ID: " + eid);
            } else {
                System.out.println("No grade record found for enrollment ID: " + eid);
            }
        } catch (Exception ex) {
            System.out.println("Failed to delete grade record: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Load record - placeholder method
     */
    public void LoadRecord() {
        System.out.println("Grades LoadRecord called");
    }
}
