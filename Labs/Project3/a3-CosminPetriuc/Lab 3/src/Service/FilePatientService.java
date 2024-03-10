package Service;
import Domain.Patient;
import Repository.IRepo;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.*;
import java.util.Map;

import java.util.Map;
public class FilePatientService implements IService<Patient> {
    private IRepo<Patient> repo;
    public FilePatientService(){
        this.repo = repo;
    }
    public FilePatientService(IRepo<Patient> repo) {
        this.repo = repo;
    }

    @Override
    public void add(Patient patient) {
        Map<Integer, Patient> patients = repo.readFromFile();
        patients.put(patient.getID(), patient);
        repo.writeToFile(patients);
    }

    @Override
    public Patient getByID(int patientId) {
        Map<Integer, Patient> patients = repo.readFromFile();
        return patients.get(patientId);
    }

    @Override
    public void update(Patient patient){
        Map<Integer, Patient> patients = repo.readFromFile();
        if (patients.containsKey(patient.getID())) {
            patients.put(patient.getID(), patient);
            repo.writeToFile(patients);
        } else {
            System.out.println("Patient does not exist");
        }
    }

    @Override
    public void delete(int patientId){
        Map<Integer, Patient> patients = repo.readFromFile();
        if (patients.containsKey(patientId)) {
            patients.remove(patientId);
            repo.writeToFile(patients);
        } else {
            System.out.println("Patient does not exist");
        }
    }

    @Override
    public Map<Integer, Patient> getAll(){
        return repo.readFromFile();
    }
}
