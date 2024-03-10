package Repository;

import Domain.Appointment;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
public class BinaryFileAppointmentRepo implements IRepo<Appointment> {
    private String fileName;

    public BinaryFileAppointmentRepo(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void writeToFile(Map<Integer, Appointment> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<Integer, Appointment> readFromFile() {
        Map<Integer, Appointment> data = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = ois.readObject();
            if (obj instanceof Map<?, ?>) {
                return (Map<Integer, Appointment>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}
