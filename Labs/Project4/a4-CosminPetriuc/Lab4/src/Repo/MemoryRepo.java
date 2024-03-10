package Repo;

import Domain.Identifiable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryRepo<T extends Identifiable<U>, U> implements IRepo<T, U> {
    private Map<U, T> data = new HashMap<>();


    @Override
    public void add(T entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity can't be null");
        if (data.containsKey(entity.getID()))
            throw new IllegalArgumentException("Entity with ID " + entity.getID() + " already exists");
        data.put(entity.getID(), entity);
    }

    @Override
    public T getById(U id) {
        if (!data.containsKey(id))
            throw new IllegalArgumentException("Entity with ID " + id + " doesn't exist");
        return data.get(id);
    }

    @Override
    public void update(T entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity can't be null");
        if (!data.containsKey(entity.getID()))
            throw new IllegalArgumentException(("Entity with ID " + entity.getID() + " doesn't exist"));
        data.put(entity.getID(), entity);
    }

    @Override
    public void delete(U id) {
        if (!data.containsKey(id))
            throw new IllegalArgumentException("Entity with ID " + id + " doesn't exist");
        data.remove(id);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(data.values());
    }
}