package Service;

import Domain.Appointment;
import Repo.DBRepo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;


public class AppointmentDBService implements IService<Appointment,Integer> {
    private final DBRepo<Appointment,Integer> appointmentRepo;
    public AppointmentDBService(DBRepo<Appointment,Integer> appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }

    @Override
    public void setConnection(Connection connection) {}

    Properties properties = new Properties();
    {
        try (FileInputStream fileInputStream = new FileInputStream("data/DB.properties")) {
            properties.load(fileInputStream);
        } catch (Exception e){
            System.err.println("Error loading DB properties " + e);
        }
        String repositoryType = properties.getProperty("RepositoryType");
        String AppointmentFile = properties.getProperty("AppointmentFile");
        String PatientFile = properties.getProperty("PatientFile");
    }

    @Override
    public void add(Appointment appointment){
        try{
            appointmentRepo.openConnection();
            appointmentRepo.add(appointment);
        } catch (Exception e) {
            System.err.println("Error adding appointment: " + e.getMessage());
        } finally {
            appointmentRepo.closeConnection();
        }
    }

    @Override
    public Appointment getById(Integer id){
        try{
            appointmentRepo.openConnection();
            return appointmentRepo.getById(id);
        } catch (Exception e) {
            System.err.println("Error getting appointment: " + e.getMessage());
            return null;
        } finally {
            appointmentRepo.closeConnection();
        }
    }

    @Override
    public void update(Appointment appointment){
        try{
            appointmentRepo.openConnection();
            appointmentRepo.update(appointment);
        } catch (Exception e) {
            System.err.println("Error updating appointment: " + e.getMessage());
        } finally {
            appointmentRepo.closeConnection();
        }
    }

    @Override
    public void delete(Integer id){
        try{
            appointmentRepo.openConnection();
            appointmentRepo.delete(id);
        } catch (Exception e) {
            System.err.println("Error deleting appointment: " + e.getMessage());
        } finally {
            appointmentRepo.closeConnection();
        }
    }

    @Override
    public List<Appointment> getAll(){
        try{
            appointmentRepo.openConnection();
            return appointmentRepo.getAll();
        } catch (Exception e) {
            System.err.println("Error getting all appointments: " + e.getMessage());
            return null;
        } finally {
            appointmentRepo.closeConnection();
        }
    }

}
