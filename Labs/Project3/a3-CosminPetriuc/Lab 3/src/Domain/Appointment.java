package Domain;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
public class Appointment implements Identifiable<Integer>, Serializable{
    private int id;
    private Date date;
    private Patient patient;

    public Appointment(int id, Date date, Patient patient) {
        this.id = id;
        this.date = date;
        this.patient = patient;
    }

    public Appointment(int id, LocalDate date, Patient patient) {
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public void setID(Integer id) {

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

    @Override
    public String toString(){
        return "Appointment{" +
                "id='" + id + '\'' +
                "Patient='" + patient + '\'' +
                "Date='" + date +
                '}';
    }

}
