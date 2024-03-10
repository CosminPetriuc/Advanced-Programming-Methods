package Repository;

import Domain.Patient;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TextFIlePatientRepo implements IRepo<Patient> {
    private String fileName;

    public TextFIlePatientRepo(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void writeToFile(Map<Integer, Patient> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Patient patient : data.values()) {
                bw.write(patient.getID() + "," + patient.getName() + "," + patient.getAge() + "," + patient.getIllness());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<Integer, Patient> readFromFile(){
        Map<Integer, Patient> data = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = reader.readLine()) !=null){
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0]);
                String name = fields[1];
                int age = Integer.parseInt(fields[2]);
                String illness = fields[3];
                Patient patient = new Patient(id, name, age, illness);
                data.put(id, patient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
