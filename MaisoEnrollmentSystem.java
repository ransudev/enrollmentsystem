package com.mycompany.esystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MaisoEnrollmentSystem {

    public static Connection con;
    public static Statement st;
    public static ResultSet rs;

    static String db = "";
    static String uname = "root";
    static String pswd = "maiso";

    public static StudentsForm studentsFormInstance;
    public static TeachersForm teachersFormInstance;
    public static SubjectsForm subjectsFormInstance;

    public static void main(String[] args) {
        Login loginForm = new Login();
        loginForm.setVisible(true);
        //StudentsForm student = new StudentsForm();
        //student.setVisible(true);
    }

    public static void DBConnect() {
        try {
            String home = "192.168.64.3";
            String school = "10.4.44.159";

            Class.forName("com.mysql.cj.jdbc.Driver");

            // Check if database name is empty
            if (db == null || db.trim().isEmpty()) {
                System.out.println("Warning: No database selected. Please select a database first.");
                throw new Exception("No database selected. Please choose a semester database.");
            }

            // Only root can create databases, for other users just connect to existing database
            if ("root".equalsIgnoreCase(uname)) {
                con = DriverManager.getConnection(
                        "jdbc:mysql://" + home + ":3306/?zeroDateTimeBehavior=CONVERT_TO_NULL&connectTimeout=5000",
                        uname, pswd
                );
                st = con.createStatement();
                st.execute("CREATE DATABASE IF NOT EXISTS `" + db + "`");
                st.close();
                con.close();
            }

            // Connect to the specific database
            con = DriverManager.getConnection(
                    "jdbc:mysql://" + home + ":3306/" + db + "?zeroDateTimeBehavior=CONVERT_TO_NULL&connectTimeout=5000",
                    uname, pswd
            );
            st = con.createStatement();

            System.out.println("Connected to database: " + db);
        } catch (Exception ex) {
            System.out.println("Failed to Connect: " + ex);
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed: " + ex.getMessage(), ex);
        }
    }
}
