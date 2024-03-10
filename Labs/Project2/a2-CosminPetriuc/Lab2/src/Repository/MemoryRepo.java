package Repository;

import Domain.Identifiable;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
public class MemoryRepo<T extends Identifiable<U>,U> implements Repo<T,U>{
    private Map<Integer, T> data = new HashMap<Integer, T>();
    private int nextId = 1;

    @Override
    public void add(T entity) {
        if(entity == null)
            throw new IllegalArgumentException("Entity can't be null");
        if(data.containsKey(entity.getID()))
            throw new IllegalArgumentException("Entity with ID " + entity.getID() + " already exists");
        entity.setId(nextId++);
        data.put(entity.getID(), entity);
    }

    @Override
    public T getById(int id) {
        if(!data.containsKey(id))
            throw new IllegalArgumentException("Entity with ID " + id + " doesn't exist");
        return data.get(id);
    }

    @Override
    public void update(T entity){
        if (entity == null)
            throw new IllegalArgumentException("Entity can't be null");
        if(!data.containsKey(entity.getID()))
            throw new IllegalArgumentException(("Entity with ID" + entity.getID() + "doesn't exist" ));
        data.put(entity.getID(), entity);
    }
    @Override
    public void delete(int id){
        if(!data.containsKey(id))
            throw new IllegalArgumentException("Entity with ID " + id + " doesn't exist");
        data.remove(id);
    }

    @Override
    public List<T> getAll(){
        return new ArrayList<T>(data.values());
    }

    private int generateId(){
        return nextId++;
    }
}