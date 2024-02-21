package Repository;

import Domain.Entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MemoryRepository<T extends Entity> implements IRepository<T> {
    protected List<T> entities = new ArrayList<>();
    @Override
    public void add(T entity) throws DuplicateEntityException, IOException, RepositoryException {
        if (entity == null)
            throw new IllegalArgumentException("entity can't be null");
        if (findById(entity.getId()) != null)
            throw new DuplicateEntityException("The id is already used!");
        entities.add(entity);
    }

    @Override
    public void remove(int id) throws IOException {
        for (T entity: entities) {
            if (entity.getId() == id) {
                entities.remove(entity);
                break;
            }
        }
    }

    @Override
    public void update(int pos, T entity) throws IOException {
        entities.set(pos, entity);
    }

    @Override
    public T findById(int id) {
        for (T entity: entities) {
            if (entity.getId() == id) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public Collection<T> getAll() {
        return new ArrayList<T>(entities);
    }

    @Override
    public void showNumberOfCakesPerDay() {

    }

    @Override
    public void showNumberOfCakesPerMont() {

    }

    @Override
    public void MostOrderedCakes() {

    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayList<T>(entities).iterator();
    }
}

