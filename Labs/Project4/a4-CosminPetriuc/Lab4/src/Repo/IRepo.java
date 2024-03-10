package Repo;

import Domain.Identifiable;
import java.util.List;

public interface IRepo<T extends Identifiable<U>, U> {
    void add(T entity);
    T getById(U id);
    void update(T entity);
    void delete(U id);
    List<T> getAll();
}