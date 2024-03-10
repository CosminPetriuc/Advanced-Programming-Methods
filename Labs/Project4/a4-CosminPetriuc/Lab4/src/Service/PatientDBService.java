package Service;

import Domain.Patient;
import Repo.DBRepo;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;
public class PatientDBService implements IService<Patient, Integer> {
    private final DBRepo<Patient, Integer> patientRepo;

    public PatientDBService(DBRepo<Patient, Integer> patientRepo) {
        this.patientRepo = patientRepo;
    }

    @Override
    public void add(Patient patient) {
        try {
            patientRepo.openConnection();
            patientRepo.add(patient);
        } catch (Exception e) {
            System.err.println("Error adding patient: " + e.getMessage());
        } finally {
            patientRepo.closeConnection();
        }
    }

    @Override
    public List<Patient> getAll() {
        try {
            patientRepo.openConnection();
            return patientRepo.getAll();
        } catch (Exception e) {
            System.err.println("Error getting all patients: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            patientRepo.closeConnection();
        }
    }

    @Override
    public void setConnection(Connection connection) {

    }

    @Override
    public Patient getById(Integer id) {
        try {
            patientRepo.openConnection();
            return patientRepo.getById(id);
        } catch (Exception e) {
            System.err.println("Error getting patient by ID: " + e.getMessage());
            return null;
        } finally {
            patientRepo.closeConnection();
        }
    }
    @Override
    public void update(Patient patient) {
        try {
            patientRepo.openConnection();
            patientRepo.update(patient);
        } catch (Exception e) {
            System.err.println("Error updating patient: " + e.getMessage());
        } finally {
            patientRepo.closeConnection();
        }
    }
    @Override
    public void delete(Integer id) {
        try {
            patientRepo.openConnection();
            patientRepo.delete(id);
        } catch (Exception e) {
            System.err.println("Error deleting patient: " + e.getMessage());
        } finally {
            patientRepo.closeConnection();
        }
    }
}
