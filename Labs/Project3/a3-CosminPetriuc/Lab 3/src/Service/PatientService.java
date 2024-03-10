package Service;

import Domain.Patient;
import Repository.PatientRepo;

import java.util.Map;
public class PatientService implements IService<Patient> {
    private PatientRepo patientRepo;

    public PatientService(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    @Override
    public void add(Patient patient){
        patientRepo.addPatient(patient);
    }

    @Override
    public Patient getByID(int patientId){
        return patientRepo.getPatient(patientId);

    }

    @Override
    public void update(Patient patient){
        patientRepo.updatePatient(patient);
    }

    @Override
    public void delete(int patientId){
        patientRepo.deletePatient(patientId);
    }

    @Override
    public Map<Integer, Patient> getAll(){
        return patientRepo.loadPatients();
    }
}
