package Service;

import Domain.Identifiable;
import Repo.IRepo;

import java.sql.Connection;
import java.util.List;
public interface IService<T extends Identifiable<U>,U> {
    void add(T entity);
    T getById(U id);
    void update(T entity);
    void delete(U id);
    List<T> getAll();
    void setConnection(Connection connection);
}
