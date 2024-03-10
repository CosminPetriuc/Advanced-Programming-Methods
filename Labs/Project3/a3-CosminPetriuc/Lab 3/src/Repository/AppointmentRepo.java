package Repository;

import Domain.Appointment;
import java.util.HashMap;
import java.util.Map;
public class AppointmentRepo {
    private IRepo<Appointment> repo;

    public AppointmentRepo(IRepo<Appointment> repo) {
        this.repo = repo;
    }

    public void saveAppointments(Map<Integer, Appointment> appointments) {
        repo.writeToFile(appointments);
    }

    public Map<Integer, Appointment> loadAppointments() {
        return repo.readFromFile();
    }

    public void addAppointment(Appointment appointment) {
        Map<Integer, Appointment> appointments = loadAppointments();
        appointments.put(appointment.getID(), appointment);
        saveAppointments(appointments);
    }

    public Appointment getAppointment(int id) {
        Map<Integer, Appointment> appointments = loadAppointments();
        return appointments.get(id);
    }

    public void updateAppointment(Appointment updateAppointment){
        Map<Integer,Appointment> appointments = loadAppointments();
        if (appointments.containsKey(updateAppointment.getID())){
            appointments.put(updateAppointment.getID(),updateAppointment);
            saveAppointments(appointments);
        }else {
            System.out.println("Appointment does not exist");

        }
    }

    public void deleteAppointment(int id){
        Map<Integer,Appointment> appointments = loadAppointments();
        if (appointments.containsKey(id)){
            appointments.remove(id);
            saveAppointments(appointments);
        }else {
            System.out.println("Appointment does not exist");
        }
    }
}
