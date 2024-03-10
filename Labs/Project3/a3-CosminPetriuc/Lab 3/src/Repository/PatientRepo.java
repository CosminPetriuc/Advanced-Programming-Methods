package Repository;

import Domain.Patient;
import java.util.HashMap;
import java.util.Map;
public class PatientRepo {
    private IRepo<Patient> repo;
    public PatientRepo(){
        this.repo = repo;
    }

    public PatientRepo(IRepo<Patient> repo) {
        this.repo = repo;
    }

    public void savePatients(Map<Integer, Patient> patients) {
        repo.writeToFile(patients);
    }

    public Map<Integer, Patient> loadPatients() {
        return repo.readFromFile();
    }

    public void addPatient(Patient patient) {
        Map<Integer, Patient> patients = loadPatients();
        patients.put(patient.getID(), patient);
        savePatients(patients);
    }

    public Patient getPatient(int id) {
        Map<Integer, Patient> patients = loadPatients();
        return patients.get(id);
    }

    public void updatePatient(Patient updatePatient){
        Map<Integer,Patient> patients = loadPatients();
        if (patients.containsKey(updatePatient.getID())){
            patients.put(updatePatient.getID(),updatePatient);
            savePatients(patients);
        }else {
            System.out.println("Patient does not exist");

        }
    }

    public void deletePatient(int id){
        Map<Integer,Patient> patients = loadPatients();
        if (patients.containsKey(id)){
            patients.remove(id);
            savePatients(patients);
        }else {
            System.out.println("Patient does not exist");
        }
    }

}
