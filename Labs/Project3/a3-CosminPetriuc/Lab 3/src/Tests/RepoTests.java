package Tests;
import Domain.Patient;
import Repository.PatientRepo;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class RepoTests {

    @Test
    public void testAddPatient(){
        PatientRepo patientRepo = new PatientRepo();
        Patient patient = new Patient(1, "John Doe", 30, "Fever");

        patientRepo.addPatient(patient);

        Map<Integer, Patient> patients = patientRepo.loadPatients();
        assertTrue(patients.containsKey(1));
        assertEquals(patient, patients.get(1));
    }

    @Test
    public void testGetPatient(){
        PatientRepo patientRepo = new PatientRepo();
        Patient patient = new Patient(1, "John Doe", 30, "Fever");

        patientRepo.addPatient(patient);

        Patient patient1 = patientRepo.getPatient(1);
        assertEquals(patient, patient1);
    }

    @Test
    public void testUpdatePatient(){
        PatientRepo patientRepo = new PatientRepo();
        Patient patient = new Patient(1, "John Doe", 30, "Fever");

        patientRepo.addPatient(patient);

        Patient patient1 = new Patient(1, "John Doe", 31, "Fever");
        patientRepo.updatePatient(patient1);

        Patient patient2 = patientRepo.getPatient(1);
        assertEquals(patient1, patient2);
    }

    @Test
    public void testDeletePatient(){
        PatientRepo patientRepo = new PatientRepo();
        Patient patient = new Patient(1, "John Doe", 30, "Fever");

        patientRepo.addPatient(patient);

        patientRepo.deletePatient(1);

        Map<Integer, Patient> patients = patientRepo.loadPatients();
        assertFalse(patients.containsKey(1));
    }

}
