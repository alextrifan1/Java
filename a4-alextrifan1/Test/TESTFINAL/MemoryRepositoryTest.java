package TESTFINAL;

import Domain.Cake;
import Repository.DuplicateEntityException;
import Repository.MemoryRepository;
import Repository.RepositoryException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MemoryRepositoryTest {

    @Test
    public void add() throws DuplicateEntityException, IOException, RepositoryException {
        Cake c = new Cake(1, "Vanilie");
        MemoryRepository<Cake> repository = new MemoryRepository<>();
        repository.add(c);
        assert repository.findById(1).getId() == 1;
    }

    @Test
    public void remove() throws DuplicateEntityException, IOException, RepositoryException {
        Cake c1 = new Cake(1, "1");
        Cake c2 = new Cake(2, "2");
        Cake c3 = new Cake(3, "3");
        MemoryRepository<Cake> repository = new MemoryRepository<>();
        repository.add(c1);
        repository.add(c2);
        repository.add(c3);
        repository.remove(2);
        assert repository.getAll().size() == 2;
    }

    @Test
    public void update() throws DuplicateEntityException, IOException, RepositoryException {
        Cake c = new Cake(1, "Vanilie");
        Cake c2 = new Cake(2, "2");
        MemoryRepository<Cake> repository = new MemoryRepository<>();
        repository.add(c);
        repository.update(0, c2);
        assert repository.findById(2).getId() == 2;
    }

    @Test
    public void findById() throws DuplicateEntityException, IOException, RepositoryException {
        Cake c = new Cake(1, "Vanilie");
        MemoryRepository<Cake> repository = new MemoryRepository<>();
        repository.add(c);
        assert repository.findById(1) == c;
    }

    @Test
    public void getAll() throws DuplicateEntityException, IOException, RepositoryException {
        Cake c1 = new Cake(1, "1");
        Cake c2 = new Cake(2, "2");
        Cake c3 = new Cake(3, "3");
        ArrayList<Cake> arrayList = new ArrayList<>();
        arrayList.add(c1);
        arrayList.add(c2);
        arrayList.add(c3);
        MemoryRepository<Cake> repository = new MemoryRepository<>();
        repository.add(c1);
        repository.add(c2);
        repository.add(c3);
        assert repository.getAll().equals(arrayList);
    }
}