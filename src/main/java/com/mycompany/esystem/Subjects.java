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
public class Subjects extends MaisoEnrollmentSystem {
    int subjId;
    String subjCode;
    String subjDesc;
    int subjUnits;
    String sched;

    Subjects() {
        // Do not automatically connect to DB in constructor
        // Connection will be handled when needed
    }

    public void connectDB() {
        this.DBConnect(); // Only connect when explicitly called
    }

    public void SaveRecord(String subjCode, String subjDesc, int subjUnits, String sched) {
        this.DBConnect();
        try {
            int nextSubjId = 2001; // Default start ID for subjects
            try {
                String maxIdQuery = "SELECT MAX(subjid) FROM Subjects";
                ResultSet rs = st.executeQuery(maxIdQuery);
                if (rs.next()) {
                    int maxId = rs.getInt(1);
                    if (maxId > 0) { // If the table is not empty
                        nextSubjId = maxId + 1;
                    }
                    // Ensure the ID is at least 2001
                    if (nextSubjId < 2001) {
                        nextSubjId = 2001;
                    }
                }
            } catch (Exception ex) {
                System.out.println("Error getting next subject ID: " + ex.getMessage());
            }

            String insertQuery = "INSERT INTO Subjects (subjid, subjcode, subjdesc, subjunits, subjsched) VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement pst = con.prepareStatement(insertQuery);
            pst.setInt(1, nextSubjId);
            pst.setString(2, subjCode);
            pst.setString(3, subjDesc);
            pst.setInt(4, subjUnits);
            pst.setString(5, sched);

            pst.executeUpdate();
            System.out.println("Insert success! New Subject record created with ID: " + nextSubjId);
        } catch (Exception ex) {
            System.out.println("Failed to insert subject: " + ex.getMessage());
        }
    }

    public void DeleteRecord(int subjId) {
        this.DBConnect();
        try {
            String query = "DELETE FROM Subjects WHERE subjid = ?";
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setInt(1, subjId);

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

    public void UpdateRecord(int subjId, String subjCode, String subjDesc, int subjUnits, String sched) {
        this.DBConnect();
        String query = "UPDATE Subjects SET subjcode = ?, subjdesc = ?, subjunits = ?, subjsched = ? WHERE subjid = ?";
        try {
            PreparedStatement pst = this.con.prepareStatement(query);
            pst.setString(1, subjCode);
            pst.setString(2, subjDesc);
            pst.setInt(3, subjUnits);
            pst.setString(4, sched);
            pst.setInt(5, subjId);

            pst.executeUpdate();
            System.out.println("Information Updated Successfully!");
        } catch (Exception ex) {
            System.out.println("Failed to Update Information. " + ex.getMessage());
        }
    }

    public void LoadRecord() {
        System.out.print("Update Clicked");
    }
}