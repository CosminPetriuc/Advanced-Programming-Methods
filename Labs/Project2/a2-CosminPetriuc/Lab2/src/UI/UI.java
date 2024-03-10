package UI;
import Domain.Appointment;
import Domain.Patient;
import Repository.MemoryRepo;
import Repository.Repo;
import Service.Service;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class UI {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    private Repo<Patient, Integer> patientRepo = new MemoryRepo<>();
    private Repo<Appointment, Integer> appointmentRepo = new MemoryRepo<>();
    private Service service = new Service(patientRepo, appointmentRepo);
    private Scanner scanner = new Scanner(System.in);


    public void run() {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            processChoice(choice);
        }
    }
    private void printMenu() {
            System.out.println("Menu");
            System.out.println("1. Add patient");
            System.out.println("2. Schedule appointment");
            System.out.println("3. Cancel appointment");
            System.out.println("4. View all patients");
            System.out.println("5. View all appointments");
            System.out.println("6. Update patient");
            System.out.println("7. Delete patient");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
        }

    private void processChoice(int choice) {
            switch (choice) {
                case 1:
                    addPatientU();
                    break;
                case 2:
                    scheduleAppointment();
                    break;
                case 3:
                    cancelAppointment();
                    break;
                case 4:
                    getAllPatients();
                    break;
                case 5:
                    getAllAppointments();
                    break;
                case 6:
                    updatePatient();
                    break;
                case 7:
                    deletePatient();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }


    public void addPatientU()
    {   int ID, age;
        String name, illness;
        System.out.print("Enter ID: ");
        ID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter age: ");
        age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter name: ");
        name = scanner.nextLine();
        System.out.print("Enter illness: ");
        illness = scanner.nextLine();
        service.addPatient(ID,name,age,illness);
    }

    public void scheduleAppointment()
    {
        int ID;
        String date;
        System.out.print("Enter patient ID: ");
        ID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter date (yyyy-MM-dd HH:mm:ss): ");
        date = scanner.nextLine();
        try {
            service.scheduleAppointment(ID, dateFormat.parse(date));
        } catch (Exception e) {
            System.out.println("Invalid date format!");
        }
    }

    public void cancelAppointment()
    {
        int appointmentId;
        System.out.print("Enter appointment ID: ");
        appointmentId = scanner.nextInt();
        scanner.nextLine();
        service.cancelAppointement(appointmentId);
    }

    public void getAllAppointments()
    {
        for (Appointment a : service.getAllAppointments()) {
            System.out.println(a);
        }
    }

    public void getAllPatients()
    {
        for (Patient p : service.getAllPatients()) {
            System.out.println(p);
        }
    }

    public void updatePatient()
    {
        int ID, age;
        String name, illness;
        System.out.print("Enter ID: ");
        ID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter age: ");
        age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter name: ");
        name = scanner.nextLine();
        System.out.print("Enter illness: ");
        illness = scanner.nextLine();
        service.update(ID,name,age,illness);
    }

    public void deletePatient()
    {
        int ID;
        System.out.print("Enter ID: ");
        ID = scanner.nextInt();
        scanner.nextLine();
        service.deletePatient(ID);
    }

}
