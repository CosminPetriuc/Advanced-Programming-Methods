package Service;
import Domain.Appointment;
import Repository.AppointmentRepo;

import java.util.Map;
public class AppointmentService implements IService<Appointment>{
    private AppointmentRepo appointmentRepo;

    public AppointmentService(AppointmentRepo appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }

    @Override
    public void add(Appointment appointment){
        appointmentRepo.addAppointment(appointment);
    }

    @Override
    public Appointment getByID(int appointmentId){
        return appointmentRepo.getAppointment(appointmentId);

    }

    @Override
    public void update(Appointment appointment){
        appointmentRepo.updateAppointment(appointment);
    }

    @Override
    public void delete(int appointmentId){
        appointmentRepo.deleteAppointment(appointmentId);
    }

    @Override
    public Map<Integer, Appointment> getAll(){
        return appointmentRepo.loadAppointments();
    }
}
