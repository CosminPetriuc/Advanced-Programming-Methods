package UI;

import Domain.Identifiable;
import Domain.Patient;
import Service.Service;
import Repository.Repository;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Service service;
    private Scanner scanner;

    public void Examples(){

        service.addPatient(1, "Richard", 12, "Carie");
        service.addPatient(2,"Vasi", 21, "Carie");
        service.addPatient(3, "Andra", 26, "Afta");
        service.addPatient(4, "Dracula", 324, "Proteza");
        service.addPatient(5,"Cosmin", 19, "Aparat dentar");

    }
    public Ui(Service service) {
        this.service = service;
        scanner = new Scanner(System.in);
    }

    public void run() {
        Examples();
        int choice;
        while(true){
            System.out.println("Menu");
            System.out.println("1. Add appointment");
            System.out.println("2. Update appointment");
            System.out.println("3. Delete appointment");
            System.out.println("4. View all appointments");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
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
                    service.addPatient(ID,name,age,illness);
                    break;
                case 2:
                    int updateID, updateAge;
                    String updateName, updateIllness;
                    System.out.print("Enter ID: ");
                    updateID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter age: ");
                    updateAge = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter name: ");
                    updateName = scanner.nextLine();
                    System.out.print("Enter illness: ");
                    updateIllness = scanner.nextLine();
                    service.updatePatient(updateID, updateName, updateAge,  updateIllness);
                    break;
                case 3:
                    int deleteID;
                    System.out.print("Enter ID: ");
                    deleteID = scanner.nextInt();
                    service.deletePatient(deleteID);
                    break;
                case 4:
                    ArrayList<Identifiable> patients =  service.getAll();
                    if(patients.isEmpty())
                    {
                        System.out.println("Empty list");
                        break;
                    }
                    else {
                        for (Identifiable p : patients)
                            System.out.println(p);
                    }
                    break;
                case 0:
                    System.out.println("Ciao mi amigo, have a nice day <3");
                    return;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }
    }
}