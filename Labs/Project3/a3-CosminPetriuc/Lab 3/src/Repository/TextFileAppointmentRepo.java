package Repository;

import Domain.Appointment;
import Domain.Patient;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class TextFileAppointmentRepo implements IRepo<Appointment> {
    private String fileName;

    public TextFileAppointmentRepo(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void writeToFile(Map<Integer, Appointment> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Appointment appointment : data.values()) {
                bw.write(appointment.getID() + "," + appointment.getDate() + "," + appointment.getPatient().getID());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<Integer, Appointment> readFromFile() {
        Map<Integer, Appointment> data = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                Date date = parseDate(parts[1]);
                int patientID = Integer.parseInt(parts[2]);
                Patient patient = new Patient(patientID, "", 0, "");
                Appointment appointment = new Appointment(id, date, patient);
                data.put(id, appointment);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private Date parseDate(String date) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}