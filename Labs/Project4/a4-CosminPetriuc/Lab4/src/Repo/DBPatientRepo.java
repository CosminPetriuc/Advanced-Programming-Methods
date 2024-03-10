package Repo;

import Domain.Patient;

import java.sql.*;
import java.util.List;


public class DBPatientRepo extends DBRepo<Patient,Integer> implements IRepo<Patient, Integer> {

    @Override
    protected void createSchema(){
        try(Statement stmt = conn.createStatement()){
            String sql = "Create Table if not exists patients(" +
                    "ID integer primary key autoincrement," +
                    "name text not null," +
                    "age integer not null," +
                    "illness text not null);";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("[ERROR] Create schema: " + e.getMessage());

        }
    }

    protected Patient buildEntity(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("ID");
        String name = resultSet.getString("name");
        Integer age = resultSet.getInt("age");
        String illness = resultSet.getString("illness");

        Patient patient = new Patient(id, name, age, illness);
        return patient;
    }
    @Override
    public void add(Patient patient) {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO patients(Name, age, illness) VALUES (?, ?, ?);")) {
            pstmt.setString(1, patient.getName());
            pstmt.setInt(2, patient.getAge());
            pstmt.setString(3, patient.getIllness());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("[ERROR] Add patient: " + e.getMessage());
        }
    }

    @Override
    public void update(Patient patient) {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE patients SET Name = ?, age = ?, illness = ? WHERE ID = ?;")) {
            pstmt.setString(1, patient.getName());
            pstmt.setInt(2, patient.getAge());
            pstmt.setString(3, patient.getIllness());
            pstmt.setInt(4, patient.getID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("[ERROR] Update patient: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "DELETE FROM patients WHERE ID = ?;")) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("[ERROR] Delete patient: " + e.getMessage());
        }
    }


    @Override
    public List<Patient> getAll(){
        try (final Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM patients;");
            while (rs.next()) {
                Patient patient = buildEntity(rs);
                System.out.println(patient);
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Get all patients: " + e.getMessage());
        }
        return null;
    }


}
