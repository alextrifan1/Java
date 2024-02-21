package Domain;

import Repository.DuplicateEntityException;
import Repository.RepositoryException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IEntityFactory<T extends Entity> {
    T createEnity(String line) throws DuplicateEntityException, IOException, RepositoryException;
    String toString(T entity);
}

