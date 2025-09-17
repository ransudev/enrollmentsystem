/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.esystem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author lancejaspera.maiso
 */
public class Enrolled extends MaisoEnrollmentSystem {
    private int subjid;

    public void setsubjid(int a) {
        this.subjid = a;
    }

    public int getsubjid() {
        return this.subjid;
    }

    // Enroll(eid int, studid int, subjid int)
    public void enrollStud(int studid) {
        try {
            this.DBConnect();
            // 1) duplicate check
            String checkSql = "SELECT 1 FROM Enroll WHERE studid = ? AND subjid = ? LIMIT 1";
            try (PreparedStatement cps = con.prepareStatement(checkSql)) {
                cps.setInt(1, studid);
                cps.setInt(2, this.subjid);
                try (ResultSet crs = cps.executeQuery()) {
                    if (crs.next()) {
                        JOptionPane.showMessageDialog(null, "Student is already enrolled in this subject.");
                        return;
                    }
                }
            }
            // 2) insert
            String sql = "INSERT INTO Enroll(studid, subjid) VALUES(?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, studid);
                ps.setInt(2, this.subjid);
                int n = ps.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Student enrolled to subject.");
                } else {
                    JOptionPane.showMessageDialog(null, "No rows inserted.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "enrollStud error: " + e.getMessage());
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "enrollStud error: " + e.getMessage());
        }
    }

    public void DropSubj(int studid) {
        try {
            this.DBConnect();
            String sql = "DELETE FROM Enroll WHERE studid = ? AND subjid = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, studid);
                ps.setInt(2, this.subjid);
                int n = ps.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Enrollment dropped.");
                } else {
                    JOptionPane.showMessageDialog(null, "No matching enrollment to drop.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "DropSubj error: " + e.getMessage());
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "DropSubj error: " + e.getMessage());
        }
    }
}
