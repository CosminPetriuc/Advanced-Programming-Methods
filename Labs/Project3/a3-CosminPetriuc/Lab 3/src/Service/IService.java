package Service;

import java.util.Map;
public interface IService<T> {

    void add(T entity);
    void delete(int entityId);

    void update(T entity);

    T getByID(int entityId);

    Map<Integer, T> getAll();


}
