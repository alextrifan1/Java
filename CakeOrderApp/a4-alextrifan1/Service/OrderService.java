package Service;

import Domain.Cake;
import Domain.Order;
import Repository.DuplicateEntityException;
import Repository.IRepository;
import Repository.RepositoryException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class OrderService {
    IRepository<Order> repository;
    public OrderService(IRepository<Order> repository) {
        this.repository = repository;
    }
    public void addOrder(int id, ArrayList<Cake> torts, String data) throws DuplicateEntityException, IOException, RepositoryException {
        repository.add(new Order(id, torts, data));
    }
    public void removeOrder(int id) throws IOException {
        repository.remove(id);
    }
    public void updateOrder(int pos, Order order) throws IOException {
        repository.update(pos, order);
    }
    public Collection<Order> getAll() {
        return repository.getAll();
    }
    public void showAllCakesOrdered() {
        repository.showNumberOfCakesPerDay();
    }
    public void showAllCakesOrderedPerMonth() {
        repository.showNumberOfCakesPerMont();
    }
    public void MostOrderedCakes() {
        repository.MostOrderedCakes();
    }
}
