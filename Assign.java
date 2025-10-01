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
public class Assign extends MaisoEnrollmentSystem {

    private int subjid;

    public void setsubjid(int a) {
        this.subjid = a;
    }

    public int getsubjid() {
        return this.subjid;
    }

    public void AssignSubj(int tid) {
        try {
            this.DBConnect();

            String checkSql = "SELECT Tid FROM Assign WHERE subjid = ? LIMIT 1";
            try ( PreparedStatement check = con.prepareStatement(checkSql)) {
                check.setInt(1, this.subjid);
                try ( ResultSet rs = check.executeQuery()) {
                    if (rs.next()) {
                        int existingTid = rs.getInt("tid");
                        if (existingTid == tid) {
                            javax.swing.JOptionPane.showMessageDialog(null, "Subject is already assigned to this teacher.");
                        } else {
                            javax.swing.JOptionPane.showMessageDialog(null, "Subject already has a teacher assigned (tid = " + existingTid + ").");
                        }
                        return;
                    }
                }
            }

            String sql = "INSERT INTO Assign(subjid, Tid) VALUES(?, ?)";
            try ( PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, this.subjid);
                ps.setInt(2, tid);
                int n = ps.executeUpdate();
                if (n > 0) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Subject assigned to teacher.");
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "No rows inserted.");
                }
            }
        } catch (java.sql.SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "AssignSubj error: " + e.getMessage());
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "AssignSubj error: " + e.getMessage());
        }
    }

    public void DeleteSubj(int tid) {
        try {
            this.DBConnect();
            String sql = "DELETE FROM Assign WHERE subjid = ? AND Tid = ?";
            try ( PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, this.subjid);
                ps.setInt(2, tid);
                int n = ps.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Assignment removed.");
                } else {
                    JOptionPane.showMessageDialog(null, "No matching assignment.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "DeleteSubj error: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "DeleteSubj error: " + e.getMessage());
        }
    }
}
