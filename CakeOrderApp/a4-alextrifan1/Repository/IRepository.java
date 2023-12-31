package Repository;

import Domain.Entity;

import java.io.IOException;
import java.util.Collection;

public interface IRepository<T extends Entity> extends Iterable<T> {
    void add(T entity) throws DuplicateEntityException, IOException, RepositoryException;
    void remove(int id) throws IOException;
    void update(int pos, T entity) throws IOException;
    T findById(int id);
    Collection<T> getAll();

    void showNumberOfCakesPerDay();
    void showNumberOfCakesPerMont();
    void MostOrderedCakes();
}
