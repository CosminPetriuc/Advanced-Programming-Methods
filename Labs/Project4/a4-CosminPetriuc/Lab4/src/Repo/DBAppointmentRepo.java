package Repo;

import Domain.Appointment;
import Domain.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

public class DBAppointmentRepo extends DBRepo<Appointment, Integer> implements IRepo<Appointment,Integer> {
    @Override
    protected void createSchema() {
        try (Statement stmt = conn.createStatement()) {
            String sql = "Create Table if not exists appointments(" +
                    "ID integer primary key autoincrement," +
                    "date text not null," +
                    "patientID integer not null," +
                    "foreign key(patientID) references patients(ID));";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("[ERROR] Create schema: " + e.getMessage());
        }
    }

    protected Appointment buildEntity(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("ID");
        Date date = resultSet.getDate("date");

        Patient patient = retrievePatientFromDB(resultSet.getInt("patientID"));
        Appointment appointment = new Appointment(id, date, patient);
        return appointment;
    }

    private Patient retrievePatientFromDB(Integer patientID){
        try(Statement stmt = conn.createStatement()){
            String sql = "Select * from patients where ID = " + patientID + ";";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                Integer id = rs.getInt("ID");
                String name = rs.getString("name");
                Integer age = rs.getInt("age");
                String illness = rs.getString("illness");
                Patient patient = new Patient(id, name, age, illness);
                return patient;
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Retrieve patient from DB: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void add(Appointment appointment) {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO appointments(date, patient_id) VALUES (?, ?);")) {
            pstmt.setDate(1, new java.sql.Date(appointment.getDate().getTime()));
            pstmt.setInt(2, appointment.getPatient().getID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("[ERROR] Add appointment: " + e.getMessage());
        }
    }

    @Override
    public void update(Appointment appointment) {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE appointments SET date = ?, patient_id = ? WHERE ID = ?;")) {
            pstmt.setDate(1, new java.sql.Date(appointment.getDate().getTime()));
            pstmt.setInt(2, appointment.getPatient().getID());
            pstmt.setInt(3, appointment.getID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("[ERROR] Update appointment: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "DELETE FROM appointments WHERE ID = ?;")) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("[ERROR] Delete appointment: " + e.getMessage());
        }
    }

    @Override
    public List<Appointment> getAll(){
        try (final Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM appointments;");
            while (rs.next()) {
                Appointment appointment = buildEntity(rs);
                System.out.println(appointment);
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Get all appointments: " + e.getMessage());
        }
        return null;
    }



}
