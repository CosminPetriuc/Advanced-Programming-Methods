package Repository;

import Domain.Identifiable;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
public class FileRepo<T extends Identifiable<Integer>> implements IRepo<T> {
    private String repositoryType;
    private String fileName;

    public FileRepo(String repositoryType, String fileName) {
        this.repositoryType = repositoryType;
        this.fileName = fileName;
    }

    @Override
    public void writeToFile(Map<Integer, T> data){
        try(ObjectOutputStream oos = createObjectOutputStream()) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<Integer, T> readFromFile(){
        try(ObjectInputStream ois = createObjectInputStream()){
            Object obj = ois.readObject();
            if(obj instanceof Map<?,?>){
                return (Map<Integer, T>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new HashMap<>();

    }

    private ObjectOutputStream createObjectOutputStream() throws IOException{
        if("binary".equalsIgnoreCase(repositoryType)){
            return new ObjectOutputStream(new FileOutputStream(fileName));
        }else{
            return new ObjectOutputStream(new FileOutputStream(fileName));
        }
    }

    private ObjectInputStream createObjectInputStream() throws IOException {
        if("binary".equalsIgnoreCase(repositoryType)){
            return new ObjectInputStream(new FileInputStream(fileName));
    }else{
            return new ObjectInputStream(new FileInputStream(fileName));
        }
    }
}
