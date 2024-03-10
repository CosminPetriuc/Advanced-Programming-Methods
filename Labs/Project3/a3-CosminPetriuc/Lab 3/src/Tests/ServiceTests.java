package Tests;
import Domain.Patient;
import Service.FilePatientService;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceTests {

    @Test
    public void testAddPatient() {
        FilePatientService patientService = new FilePatientService();
        Patient patient = new Patient(1, "John Doe", 30, "Fever");

        patientService.add(patient);

        Map<Integer, Patient> patients = patientService.getAll();
        assertTrue(patients.containsKey(1));
        assertEquals(patient, patients.get(1));
    }

    @Test
    public void testGetPatientByID() {
        FilePatientService patientService = new FilePatientService();
        Patient patient = new Patient(1, "John Doe", 30, "Fever");
        patientService.add(patient);

        Patient retrievedPatient = patientService.getByID(1);

        assertNotNull(retrievedPatient);
        assertEquals(patient, retrievedPatient);
    }

    @Test
    public void testUpdatePatient() {
        FilePatientService patientService = new FilePatientService();
        Patient patient = new Patient(1, "John Doe", 30, "Fever");
        patientService.add(patient);

        Patient updatedPatient = new Patient(1, "Jane Doe", 25, "Cough");
        patientService.update(updatedPatient);

        Map<Integer, Patient> patients = patientService.getAll();
        assertTrue(patients.containsKey(1));
        assertEquals(updatedPatient, patients.get(1));
    }

    @Test
    public void testDeletePatient(){
        FilePatientService patientService = new FilePatientService();
        Patient patient = new Patient(1, "John Doe", 30, "Fever");
        patientService.add(patient);

        Patient deletedPatient = patientService.getByID(1);
        patientService.delete(1);

        Map<Integer, Patient> patients = patientService.getAll();
        assertFalse(patients.containsKey(1));
        assertEquals(deletedPatient, patient);
    }

}
