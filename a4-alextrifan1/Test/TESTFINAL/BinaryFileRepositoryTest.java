package TESTFINAL;

import Domain.Cake;
import Domain.CakeFactory;
import Repository.BinaryFileRepository;
import Repository.DuplicateEntityException;
import Repository.IRepository;
import Repository.MemoryRepository;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class BinaryFileRepositoryTest {


    @Test
    public void add() throws DuplicateEntityException, IOException, ClassNotFoundException {
//        File fileName = new File("C:\\Users\\alexandru\\Documents\\GitHub\\a3-alextrifan1\\Test\\TESTFINAL\\testFileBin.bin");
//        IRepository<Cake> memRepo = new MemoryRepository<>();
//        ArrayList<Cake> cakes = (ArrayList<Cake>) memRepo.getAll();
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
//        CakeFactory cakeFactory = new CakeFactory();
//        String filePath = fileName.getPath();
//        IRepository<Cake> repository = new BinaryFileRepository<>(filePath, cakeFactory);
//        assert repository.getAll().size() == 3;
    }

    @Test
    public void remove() {
    }

    @Test
    public void update() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void getAll() {
    }
}