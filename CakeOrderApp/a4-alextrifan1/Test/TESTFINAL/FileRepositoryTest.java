package TESTFINAL;

import Domain.Cake;
import Domain.CakeFactory;
import Repository.DuplicateEntityException;
import Repository.FileRepository;
import Repository.RepositoryException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.*;

public class FileRepositoryTest {

    @Test
    public void add() throws DuplicateEntityException, IOException, RepositoryException {
        CakeFactory cakeFactory = new CakeFactory();
        FileRepository<Cake> fileRepository = new FileRepository<>("C:\\Users\\alexandru\\Documents\\GitHub\\a4-alextrifan1\\Test\\TESTFINAL\\testFile.txt", cakeFactory);
        if (!(fileRepository.getAll().size() != 0)) {
            fileRepository.remove(0);
        }
        Cake c = new Cake(0, "Vanilie");
        fileRepository.add(c);
        assert fileRepository.findById(0).getId() == 0;
        fileRepository.remove(0);
    }

    @Test
    public void remove() throws DuplicateEntityException, IOException, RepositoryException {
        CakeFactory cakeFactory = new CakeFactory();
        FileRepository<Cake> fileRepository = new FileRepository<>("C:\\Users\\alexandru\\Documents\\GitHub\\a4-alextrifan1\\Test\\TESTFINAL\\testFile.txt", cakeFactory);
        fileRepository.remove(0);
        assert fileRepository.getAll().isEmpty();
    }

    @Test
    public void update() throws DuplicateEntityException, IOException, RepositoryException {
        CakeFactory cakeFactory = new CakeFactory();
        FileRepository<Cake> fileRepository = new FileRepository<>("C:\\Users\\alexandru\\Documents\\GitHub\\a4-alextrifan1\\Test\\TESTFINAL\\testFile.txt", cakeFactory);
        Cake c = new Cake(1, "Vanilie");
        fileRepository.add(c);
        Cake c2 = new Cake(2, "2");
        fileRepository.update(0, c2);
        assert fileRepository.findById(2).getId() == 2;
        fileRepository.remove(2);
        fileRepository.remove(0);
    }

    @Test
    public void findById() throws DuplicateEntityException, IOException, RepositoryException {
        CakeFactory cakeFactory = new CakeFactory();
        FileRepository<Cake> fileRepository = new FileRepository<>("C:\\Users\\alexandru\\Documents\\GitHub\\a4-alextrifan1\\Test\\TESTFINAL\\testFile.txt", cakeFactory);
        Cake c2 = new Cake(2, "2");
        fileRepository.add(c2);
        assert Objects.equals(fileRepository.findById(2), c2);
        fileRepository.remove(2);
    }

    @Test
    public void getAll() throws DuplicateEntityException, IOException, RepositoryException {
        CakeFactory cakeFactory = new CakeFactory();
        FileRepository<Cake> fileRepository = new FileRepository<>("C:\\Users\\alexandru\\Documents\\GitHub\\a4-alextrifan1\\Test\\TESTFINAL\\testFile.txt", cakeFactory);
        Cake c2 = new Cake(2, "2");
        fileRepository.add(c2);
        assert fileRepository.getAll().size() == 1;
        fileRepository.remove(2);
    }
}