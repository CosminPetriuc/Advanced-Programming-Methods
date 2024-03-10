package Repository;
import  Domain.Identifiable;
import java.util.List;
public interface Repo <T extends Identifiable<U>, U>{
    void add(T entity);
    T getById(int id);
    void update(T entity);
    void delete(int id);
    List<T> getAll();
}