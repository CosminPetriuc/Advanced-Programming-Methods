package Repository;

import Domain.*;

import java.util.List;
import java.util.ArrayList;

public class Repository {
    private ArrayList<Identifiable> patients = new ArrayList<Identifiable>();


    public void addPatient(Identifiable p)
    {
        patients.add(p);
    }

    public void deletePatient(Identifiable p)
    {
        patients.remove(p);
    }

    public ArrayList<Identifiable> getAll()
    {
        return this.patients;
    }
}
