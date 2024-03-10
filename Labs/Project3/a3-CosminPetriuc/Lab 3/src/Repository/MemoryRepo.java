package Repository;

import Domain.Identifiable;

import java.util.HashMap;
import java.util.Map;

public class MemoryRepo<T extends Identifiable<Integer>> implements IRepo<T> {
    private Map<Integer, T> data;

    public MemoryRepo() {
        this.data = new HashMap<>();
    }

    @Override
    public void writeToFile(Map<Integer, T> data) {
        this.data = new HashMap<>(data);
    }

    @Override
    public Map<Integer, T> readFromFile() {
        return new HashMap<>(data);
    }
}
