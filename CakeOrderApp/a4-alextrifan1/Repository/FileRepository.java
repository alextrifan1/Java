package Repository;

import Domain.Entity;
import Domain.IEntityFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class FileRepository<T extends Entity> extends MemoryRepository<T> {
    private String fileName;
    private IEntityFactory<T> entityFactory;

    public FileRepository(String fileName, IEntityFactory<T> entityFactory) throws IOException, DuplicateEntityException, RepositoryException {
        this.fileName = fileName;
        this.entityFactory = entityFactory;
        readFromFile();
    }

    private void readFromFile() throws IOException, DuplicateEntityException, RepositoryException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        /// hasNext merge pana se termina liniile din fisier
        while (scanner.hasNext()) {
            String line = scanner.nextLine(); // citeste linie cu linie
            T entity = entityFactory.createEnity(line);
            super.add(entity);
        }
        /// nu uita asta
        scanner.close();
    }

    private void saveToFile() throws IOException {

        try (FileWriter fw = new FileWriter(fileName)) {
            for (T entity : entities) {
                fw.write(entityFactory.toString(entity));
                fw.write("\r\n");
            }
        }
    }

    @Override
    public void add(T entity) throws DuplicateEntityException, IOException, RepositoryException {
        if (entity == null)
            throw new IllegalArgumentException("entity can't be null");
        if (findById(entity.getId()) != null)
            throw new DuplicateEntityException("The id is already used!");
        super.add(entity);
        saveToFile();
    }

    @Override
    public void update(int pos, T entity) throws IOException {
        super.update(pos, entity);
        saveToFile();
    }

    @Override
    public void remove(int id) throws IOException {
        super.remove(id);
        saveToFile();
    }

}






