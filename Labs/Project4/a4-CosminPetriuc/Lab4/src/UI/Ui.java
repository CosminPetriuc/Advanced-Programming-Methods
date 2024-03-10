package UI;

import Domain.Patient;
import Domain.Appointment;
import Service.IService;

import java.sql.Connection;
import java.util.Date;
import java.util.Scanner;
import java.util.Objects;
import java.util.Collection;
public class Ui<PatientServiceType extends IService<Patient,Integer>, AppointmentServiceType extends IService<Appointment,Integer>> {
    private final PatientServiceType patientService;
    private final AppointmentServiceType appointmentService;
    private final Connection conn;

    public Ui(PatientServiceType patientService, AppointmentServiceType appointmentService, Connection conn){
        this.patientService = patientService;
        this.appointmentService = appointmentService;
        this.conn = conn;
    }

    public void run(){
        while(true){
            printMainMenu();
            Scanner scanner = new Scanner(System.in);
            int command = scanner.nextInt();
            switch (command){
                case 1:
                    AppointmentMenu();
                    break;
                case 2:
                    PatientMenu();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
    }

    public void printMainMenu(){
        System.out.println("1. Appointment menu");
        System.out.println("2. Patient menu");
        System.out.println("0. Exit");
    }

    public void AppointmentMenu(){
        printAppointmentMenu();
        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        switch (command){
            case 1:
                addAppointment();
                break;
            case 2:
                updateAppointment();
                break;
            case 3:
                deleteAppointment();
                break;
            case 4:
                printAllAppointments();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid command");
                break;
        }
    }

    public void printAppointmentMenu(){
        System.out.println("1. Add appointment");
        System.out.println("2. Update appointment");
        System.out.println("3. Delete appointment");
        System.out.println("4. Print all appointments");
        System.out.println("0. Exit");
    }

    public void addAppointment() {
        Scanner scanner = new Scanner(System.in);
        int id = 0;
        System.out.println("Enter patient ID: ");
        id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter date(yyyy-MM-dd): ");
        String date = scanner.nextLine();
        System.out.println("Enter patient ID: ");
        Integer patientId = scanner.nextInt();
        Patient patient = this.patientService.getById(patientId);

        if (patient != null) {
            Appointment appointment = new Appointment(id, java.sql.Date.valueOf(date), patient);
            this.appointmentService.add(appointment);
        } else {
            System.out.println("Patient does not exist!");
        }
    }

    public void deleteAppointment() {
        if (this.appointmentService.getAll() == null){
            System.out.println("No appointments!");
            return;
        }
        System.out.println("Enter appointment ID: ");
        Scanner scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();
        this.appointmentService.delete(id);
    }

    public void updateAppointment(){
        if(this.appointmentService.getAll() == null){
            System.out.println("No appointments!");
            return;
        }
        System.out.println("Enter appointment ID: ");
        Scanner scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter date(yyyy-MM-dd): ");
        String date = scanner.nextLine();
        System.out.println("Enter patient ID: ");
        Integer patientId = scanner.nextInt();
        Patient patient = this.patientService.getById(patientId);

        if (patient != null) {
            Appointment appointment = new Appointment(id, java.sql.Date.valueOf(date), patient);
            this.appointmentService.update(appointment);
        } else {
            System.out.println("Patient does not exist!");
        }

    }

    void printAllAppointments(){
        Collection<Appointment> appointments = this.appointmentService.getAll();
        if (appointments == null){
            System.out.println("No appointments!");
            return;
        }
        for (Appointment appointment : appointments){
            System.out.println(appointment);
        }
    }


    public void PatientMenu(){
        printPatientMenu();
        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        switch (command){
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
                printAllPatients();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid command");
                break;
        }
    }

    public void printPatientMenu(){
        System.out.println("1. Add patient");
        System.out.println("2. Update patient");
        System.out.println("3. Delete patient");
        System.out.println("4. Print all patients");
        System.out.println("0. Exit");
    }

    public void addPatient(){
        Scanner scanner = new Scanner(System.in);
        int id = 0;
        System.out.println("Enter patient ID: ");
        id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.println("Enter patient age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter patient illness: ");
        String illness = scanner.nextLine();
        Patient patient = new Patient(id, name, age, illness);
        this.patientService.add(patient);
    }

    public void updatePatient(){
        if(this.patientService.getAll() == null){
            System.out.println("No patients!");
            return;
        }
        System.out.println("Enter patient ID: ");
        Scanner scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.println("Enter patient age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter patient illness: ");
        String illness = scanner.nextLine();
        Patient patient = new Patient(id, name, age, illness);
        this.patientService.update(patient);
    }

    public void deletePatient(){
        if(this.patientService.getAll() == null){
            System.out.println("No patients!");
            return;
        }
        System.out.println("Enter patient ID: ");
        Scanner scanner = new Scanner(System.in);
        Integer id = scanner.nextInt();
        this.patientService.delete(id);
    }

    void printAllPatients(){
        Collection<Patient> patients = this.patientService.getAll();
        if (patients == null){
            System.out.println("No patients!");
            return;
        }
        for (Patient patient : patients){
            System.out.println(patient);
        }
    }

}