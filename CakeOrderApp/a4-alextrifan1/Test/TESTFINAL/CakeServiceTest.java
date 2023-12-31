package TESTFINAL;

import Domain.Cake;
import Repository.DuplicateEntityException;
import Repository.IRepository;
import Repository.MemoryRepository;
import Repository.RepositoryException;
import Service.CakeService;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.*;

public class CakeServiceTest {

    @Test
    public void addCake() throws DuplicateEntityException, IOException, RepositoryException {
        IRepository<Cake> repository = new MemoryRepository<>();
        CakeService cakeService = new CakeService(repository);
        cakeService.addCake(1, "Vanilie");
        assert cakeService.getAll().size() == 1;
    }

    @Test
    public void removeCake() throws DuplicateEntityException, IOException, RepositoryException {
        IRepository<Cake> repository = new MemoryRepository<>();
        CakeService cakeService = new CakeService(repository);
        cakeService.addCake(1, "Vanilie");
        cakeService.removeCake(1);
        assert cakeService.getAll().isEmpty();
    }

    @Test
    public void updateCake() throws DuplicateEntityException, IOException, RepositoryException {
        IRepository<Cake> repository = new MemoryRepository<>();
        CakeService cakeService = new CakeService(repository);
        cakeService.addCake(1, "Vanilie");
        Cake c = new Cake(2, "2");
        cakeService.updateCake(0, c);
        assert cakeService.findById(2).getId() == 2;
        assert Objects.equals(cakeService.findById(2).getcakeType(), "2");
    }

    @Test
    public void findById() throws DuplicateEntityException, IOException, RepositoryException {
        IRepository<Cake> repository = new MemoryRepository<>();
        CakeService cakeService = new CakeService(repository);
        cakeService.addCake(1, "Vanilie");
        assert cakeService.findById(1).getId() == 1;
    }

    @Test
    public void getAll() throws DuplicateEntityException, IOException, RepositoryException {
        IRepository<Cake> repository = new MemoryRepository<>();
        CakeService cakeService = new CakeService(repository);
        cakeService.addCake(1, "1");
        cakeService.addCake(2, "3");
        cakeService.addCake(3, "4");
        assert cakeService.getAll().size() == 3;

    }
}