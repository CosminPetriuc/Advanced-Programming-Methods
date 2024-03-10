package Repository;

import Domain.Patient;
import java.io.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
public class BinaryFilePatientRepo implements IRepo<Patient> {
    private String fileName;

    public BinaryFilePatientRepo(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void writeToFile(Map<Integer, Patient> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<Integer, Patient> readFromFile() {
        Map<Integer, Patient> data = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = ois.readObject();
            if (obj instanceof Map<?, ?>) {
                return (Map<Integer, Patient>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}
