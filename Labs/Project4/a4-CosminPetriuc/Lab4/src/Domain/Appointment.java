package Domain;

import java.util.Date;

public class Appointment implements Identifiable<Integer> {
    private int id;
    private Date date;
    private Patient patient;

    public Appointment(int id, Date date, Patient patient) {
        this.id = id;
        this.date = date;
        this.patient = patient;
    }
    public Integer getID() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }
    public Date getDate() {
        return date;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String toString(){
        return "Appointment{" +
                "id='" + id + '\'' +
                "Patient='" + patient + '\'' +
                "Date='" + date +
                '}';
    }
}
