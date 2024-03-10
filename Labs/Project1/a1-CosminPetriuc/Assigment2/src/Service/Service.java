package Service;

import Repository.Repository;
import Domain.*;

import java.util.ArrayList;
import java.util.List;
public class Service {
    private Repository repo;

    public Service(Repository r)
    {
        this.repo = r;
    }

    //Add a new patient in repository with the provided specifications
    public void addPatient(int ID, String name, int age, String illness)
    {
        Patient p = new Patient(ID, name, age, illness);
        this.repo.addPatient(p);
    }

    //Delete a patient from repository based on their id
    public void deletePatient(int ID)
    {
        List<Identifiable> patientsList;
        patientsList = getAll();

        for (Identifiable p : patientsList)
        {
            if (p.getID() == ID)
            {
                this.repo.deletePatient(p);
                System.out.println("Patient with id " + p.getID() + " was deleted!");
                return;
            }
        }
        System.out.println("Patient not found: " + ID);
    }

    //Update the patient from repository based on their id
    public void updatePatient(int ID, String name, int age, String illness)
    {
        List<Identifiable> patientsList = getAll();

        for (Identifiable p : patientsList)
        {
            if (p.getID() == ID)
            {
                p.setName(name);
                p.setAge(age);
                p.setIllness(illness);
                System.out.println("Name for patient " + p.getID() + " changed to " + name + ", age " + p.getAge() + " changed to " + age + ", illness " + p.getIllness() + " changed to " + illness);
                return;
            }
        }
        System.out.println("Patient with ID " + ID + " not found.");
    }

    // Retrieve a list of all patients in the repository
    public ArrayList<Identifiable> getAll()
    {
        return this.repo.getAll();
    }
}
