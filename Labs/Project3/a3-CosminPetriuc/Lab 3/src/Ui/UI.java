package Ui;
import Domain.Appointment;
import Domain.Patient;
import Service.IService;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.Map;
public class UI {
    private IService<Patient> patientService;
    private IService<Appointment> appointmentService;

    public UI(IService<Patient> patientService, IService<Appointment> appointmentService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do{
            System.out.println("Medical Records Management System");
            System.out.println("1. Manage patients");
            System.out.println("2. Manage appointments");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    managePatients();
                    break;
                case 2:
                    manageAppointments();
                    break;
                case 0:
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while(choice != 0);
    }

    private void managePatients() {
        Scanner scanner = new Scanner(System.in);
        int patientChoice;
        do{
            System.out.println("Manage patients");
            System.out.println("1. Add patient");
            System.out.println("2. Update patient");
            System.out.println("3. Delete patient");
            System.out.println("4. Show all patients");
            System.out.println("5. View patient");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");
            patientChoice = scanner.nextInt();
            switch (patientChoice){
                case 1:
                    addPatient();
                    break;
                case 2:
                    updatePatient();
                    break;
                case 3:
                    deletePatient();
                    break;
                case 4:
                    showAllPatients();
                    break;
                case 5:
                    viewPatient();
                    break;
                case 0:
                    System.out.println("Back!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while(patientChoice != 0);
    }

    private void addPatient(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter patient ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter patient name: ");
        String name = scanner.next();
        System.out.print("Enter patient age: ");
        int age = scanner.nextInt();
        System.out.print("Enter patient illness: ");
        String illness = scanner.next();
        patientService.add(new Patient(id, name, age, illness));

    }

    private void updatePatient(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter patient ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter patient name: ");
        String name = scanner.next();
        System.out.print("Enter patient age: ");
        int age = scanner.nextInt();
        System.out.print("Enter patient illness: ");
        String illness = scanner.next();
        patientService.update(new Patient(id, name, age, illness));
    }

    private void deletePatient(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter patient ID: ");
        int id = scanner.nextInt();
        patientService.delete(id);
    }

    private void showAllPatients(){
        Map<Integer, Patient> patients = patientService.getAll();
        for(Patient patient : patients.values()){
            System.out.println(patient);
        }
    }

    private void viewPatient(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter patient ID: ");
        int id = scanner.nextInt();
        Patient patient = patientService.getByID(id);
        System.out.println(patient);
    }

    private void manageAppointments() {
        Scanner scanner = new Scanner(System.in);
        int appointmentChoice;
        do{
            System.out.println("Manage appointments");
            System.out.println("1. Schedule appointment");
            System.out.println("2. Cancel appointment");
            System.out.println("3. Show all appointments");
            System.out.println("4. Update appointment");
            System.out.println("5. View appointment");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");
            appointmentChoice = scanner.nextInt();
            switch (appointmentChoice){
                case 1:
                    scheduleAppointment();
                    break;
                case 2:
                    cancelAppointment();
                    break;
                case 3:
                    showAllAppointments();
                    break;
                case 4:
                    updateAppointment();
                    break;
                case 5:
                    viewAppointment();
                    break;
                case 0:
                    System.out.println("Back!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while(appointmentChoice != 0);
    }



    private void scheduleAppointment() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter appointment ID: ");
        int id = scanner.nextInt();
        System.out.println("Enter appointment date(YYYY-MM-DD): ");
        String dateString = scanner.next();
        LocalDate date = LocalDate.parse(dateString);
        System.out.println("Enter patient ID: ");
        int patientId = scanner.nextInt();

        Appointment newAppointment = new Appointment(id, date, new Patient(patientId, "", 0, ""));
        appointmentService.add(newAppointment);

        System.out.println("Appointment scheduled successfully!");
    }

    private void viewAppointment(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter appointment ID: ");
        int appointmentId = scanner.nextInt();

        Appointment appointment = appointmentService.getByID(appointmentId);
        if (appointment != null){
            System.out.println("Appointemnt found: " + appointment);
        } else {
            System.out.println("Appointment not found!");
        }
    }

    private void updateAppointment() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter appointment ID: ");
        int id = scanner.nextInt();

        System.out.println("Enter new date(YYYY-MM-DD): ");
        String dateString = scanner.next();
        LocalDate date = LocalDate.parse(dateString);

        System.out.println("Enter new patient ID: ");
        int patientId = scanner.nextInt();

        Appointment updateAppointment = new Appointment(id, date, new Patient(patientId, "", 0, ""));
        appointmentService.update(updateAppointment);

        System.out.println("Appointment updated successfully!");

    }

    private void showAllAppointments(){
        Map<Integer, Appointment> appointments = appointmentService.getAll();
        if (appointments.isEmpty()){
            System.out.println("There are no appointments!");
        } else {
            System.out.println("Appointments: ");
            for (Appointment appointment : appointments.values()){
                System.out.println(appointment);
            }
        }
    }

    private void cancelAppointment(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter appointment ID: ");
        int appointmentId = scanner.nextInt();

        appointmentService.delete(appointmentId);

        System.out.println("Appointment cancelled successfully!");
    }
}

