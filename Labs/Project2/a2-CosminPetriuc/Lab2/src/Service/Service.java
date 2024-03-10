package Service;

import Domain.Appointment;
import Domain.Patient;
import Repository.Repo;
import java.util.List;

import java.util.Date;

public class Service {
    private Repo<Patient, Integer> patientRepo;
    private Repo<Appointment, Integer> appointmentRepo;

    public Service(Repo<Patient, Integer> patientRepo, Repo<Appointment, Integer> appointmentRepo) {
        this.patientRepo = patientRepo;
        this.appointmentRepo = appointmentRepo;
    }

    public void addPatient(int ID, String name, int age, String illness)
    {
        Patient p = new Patient(ID, name, age, illness);
        this.patientRepo.add(p);
    }

    public void scheduleAppointment(int ID, Date date)
    {
        Patient patient = patientRepo.getById(ID);
        if(patient == null)
        {
            throw new IllegalArgumentException("Patient with ID" + patient.getID() + "does not exist!");
        }

        int appointmentId = getNextAppointmentId();
        Appointment newAppointment = new Appointment(appointmentId, date, patient);
        appointmentRepo.add(newAppointment);

    }

    public void cancelAppointement(int appointmentId)
    {
        appointmentRepo.delete(appointmentId);
    }

    public List<Appointment> getAllAppointments()
    {
        return appointmentRepo.getAll();
    }

    public List<Patient> getAllPatients()
    {
        return patientRepo.getAll();
    }

    public void update (int ID, String name, int age, String illness)
    {
        Patient patient = patientRepo.getById(ID);
        if(patient == null)
        {
            throw new IllegalArgumentException("Patient with ID" + patient.getID() + "does not exist!");
        }
        patient.setName(name);
        patient.setAge(age);
        patient.setIllness(illness);
        patientRepo.update(patient);
    }

    public void deletePatient(int ID)
    {
        patientRepo.delete(ID);
    }

    private int getNextAppointmentId(){
        List<Appointment> appointments = appointmentRepo.getAll();
        return appointments.isEmpty() ? 1 : appointments.get(appointments.size() - 1).getID() + 1;
    }

}
