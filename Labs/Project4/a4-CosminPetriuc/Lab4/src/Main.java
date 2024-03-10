import Domain.*;
import Repo.*;
import Service.*;
import UI.Ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main{
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:A4.sqlite");

            DBPatientRepo patientRepo = new DBPatientRepo();
            DBAppointmentRepo appointmentRepo = new DBAppointmentRepo();


            PatientDBService patientService = new PatientDBService(patientRepo);
            AppointmentDBService appointmentService = new AppointmentDBService(appointmentRepo);

            patientService.setConnection(conn);
            appointmentService.setConnection(conn);

            Ui<PatientDBService, AppointmentDBService> ui = new Ui<>(patientService, appointmentService, conn);
            ui.run();

            conn.close();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }
}