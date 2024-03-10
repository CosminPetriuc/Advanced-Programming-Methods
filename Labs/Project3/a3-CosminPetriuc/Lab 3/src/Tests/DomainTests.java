package Tests;
import Domain.Patient;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DomainTests {

    @Test
    public void testGetID(){
        Patient patient = new Patient(1, "Jhon", 30,"Fever");
        assertEquals(1, patient.getID());
    }

    @Test
    public void testSetID(){
        Patient patient = new Patient(1, "Jhon", 30,"Fever");
        patient.setID(2);
        assertEquals(2, patient.getID());
    }

    @Test
    public void testGetName(){
        Patient patient = new Patient(1, "Jhon", 30,"Fever");
        assertEquals("Jhon", patient.getName());
    }

    @Test
    public void testSetName(){
        Patient patient = new Patient(1, "Jhon", 30,"Fever");
        patient.setName("Jhonny");
        assertEquals("Jhonny", patient.getName());
    }

    @Test
    public void testGetAge(){
        Patient patient = new Patient(1, "Jhon", 30,"Fever");
        assertEquals(30, patient.getAge());
    }

    @Test
    public void testSetAge(){
        Patient patient = new Patient(1, "Jhon", 30,"Fever");
        patient.setAge(31);
        assertEquals(31, patient.getAge());
    }

    @Test
    public void testGetIllness(){
        Patient patient = new Patient(1, "Jhon", 30,"Fever");
        assertEquals("Fever", patient.getIllness());
    }

    @Test
    public void testSetIllness(){
        Patient patient = new Patient(1, "Jhon", 30,"Fever");
        patient.setIllness("Cough");
        assertEquals("Cough", patient.getIllness());
    }

    @Test
    public void testGetPatient(){
        Patient patient = new Patient(1, "Jhon", 30,"Fever");
        Patient patient1 = new Patient(1, "Jhon", 30,"Fever");
        assertEquals(patient1, patient);

    }

}
