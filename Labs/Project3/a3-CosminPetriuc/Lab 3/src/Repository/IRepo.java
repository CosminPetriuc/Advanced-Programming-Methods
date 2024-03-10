package Repository;

import Domain.Identifiable;

import java.util.Map;
public interface IRepo<T extends Identifiable<Integer>> {
    void writeToFile(Map<Integer, T> data);
    Map<Integer, T> readFromFile();

}
