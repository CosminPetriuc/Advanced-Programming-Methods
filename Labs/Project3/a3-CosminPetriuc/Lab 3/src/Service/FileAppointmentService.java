package Service;
import Domain.Appointment;
import Repository.IRepo;

import java.util.Map;
public class FileAppointmentService implements IService<Appointment> {
    private IRepo<Appointment> repo;

    public FileAppointmentService(IRepo<Appointment> repo) {
        this.repo = repo;
    }

    @Override
    public void add(Appointment appointment) {
        Map<Integer, Appointment> appointments = repo.readFromFile();
        appointments.put(appointment.getID(), appointment);
        repo.writeToFile(appointments);
    }

    @Override
    public Appointment getByID(int appointmentId) {
        Map<Integer, Appointment> appointments = repo.readFromFile();
        return appointments.get(appointmentId);
    }

    @Override
    public void update(Appointment appointment){
        Map<Integer, Appointment> appointments = repo.readFromFile();
        if (appointments.containsKey(appointment.getID())) {
            appointments.put(appointment.getID(), appointment);
            repo.writeToFile(appointments);
        } else {
            System.out.println("Appointment does not exist");
        }
    }

    @Override
    public void delete(int appointmentId){
        Map<Integer, Appointment> appointments = repo.readFromFile();
        if (appointments.containsKey(appointmentId)) {
            appointments.remove(appointmentId);
            repo.writeToFile(appointments);
        } else {
            System.out.println("Appointment does not exist");
        }
    }

    @Override
    public Map<Integer, Appointment> getAll(){
        return repo.readFromFile();
    }


}
