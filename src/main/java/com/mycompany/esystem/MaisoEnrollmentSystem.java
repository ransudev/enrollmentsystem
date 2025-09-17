package com.mycompany.esystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MaisoEnrollmentSystem {

    public static Connection con;
    public static Statement st;
    public static ResultSet rs;

    static String db = "1st_SY2025_2026";
    static String uname = "root";
    static String pswd = "maiso";

    public static StudentsForm studentsFormInstance;
    public static TeachersForm teachersFormInstance;
    public static SubjectsForm subjectsFormInstance;

    public static void main(String[] args) {
        // DBConnect(); // We will connect after the user logs in
        Login loginForm = new Login();
        loginForm.setVisible(true);
        //StudentsForm student = new StudentsForm();
        //student.setVisible(true);
    }

    public static void DBConnect() {
        try {
            String home = "192.168.64.3";

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                "jdbc:mysql://" + home + ":3306/?zeroDateTimeBehavior=CONVERT_TO_NULL&connectTimeout=5000",
                uname, pswd
            );
            st = con.createStatement();
            st.execute("CREATE DATABASE IF NOT EXISTS " + db + "");
            st.close();
            con.close();

            con = DriverManager.getConnection(
                "jdbc:mysql://" + home + ":3306/" + db + "?zeroDateTimeBehavior=CONVERT_TO_NULL&connectTimeout=5000",
                uname, pswd
            );
            st = con.createStatement();

            System.out.println("Connected to database: " + db);
        } catch (Exception ex) {
            System.out.println("Failed to Connect: " + ex);
            ex.printStackTrace();
        }
    }
}