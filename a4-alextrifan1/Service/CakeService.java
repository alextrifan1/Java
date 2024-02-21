package Service;

import Domain.Cake;
import Repository.DuplicateEntityException;
import Repository.IRepository;
import Repository.RepositoryException;

import java.io.IOException;
import java.util.Collection;

public class CakeService {
    IRepository<Cake> repository;
    public CakeService(IRepository<Cake> repository) {
        this.repository = repository;
    }

    public void addCake(int id, String cakeType) throws DuplicateEntityException, IOException, RepositoryException {
        repository.add(new Cake(id, cakeType));
    }
    public void removeCake(int id) throws IOException {
        repository.remove(id);
    }
    public void updateCake(int pos, Cake cake) throws IOException {
        repository.update(pos, cake);
    }
    public Cake findById(int id) {
        return repository.findById(id);
    }
    public Collection<Cake> getAll() {
        return repository.getAll();
    }
}
