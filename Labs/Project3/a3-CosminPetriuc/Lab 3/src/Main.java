import Domain.Patient;
import Domain.Appointment;
import Repository.BinaryFileAppointmentRepo;
import Repository.BinaryFilePatientRepo;
import Repository.TextFIlePatientRepo;
import Repository.TextFileAppointmentRepo;
import Service.FileAppointmentService;
import Service.FilePatientService;
import Service.IService;
import Ui.UI;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Properties;


public class Main{
    public static void main(String[] args) {

        Properties properties = new Properties();
        try (FileInputStream input = new  FileInputStream("settings.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Determine the repository type from the settings
        String repositoryType = properties.getProperty("Repository", "text"); // Default to text if not specified

        // Create binary (.bin) or text (.txt) files for patients and appointments
        String patientFileName = properties.getProperty("Patients", "patients.txt");
        String appointmentFileName = properties.getProperty("Appointments", "appointments.txt");

        IService<Patient> patientService;
        IService<Appointment> appointmentService;

        if ("binary".equalsIgnoreCase(repositoryType)) {
            patientService = new FilePatientService(new BinaryFilePatientRepo(patientFileName));
            appointmentService = new FileAppointmentService(new BinaryFileAppointmentRepo(appointmentFileName));
        } else {
            patientService = new FilePatientService(new TextFIlePatientRepo(patientFileName));
            appointmentService = new FileAppointmentService(new TextFileAppointmentRepo(appointmentFileName));
        }

        UI ui = new UI(patientService, appointmentService);
        ui.start();

    }
}