package TESTFINAL;

import Domain.Order;
import Repository.DuplicateEntityException;
import Repository.IRepository;
import Repository.MemoryRepository;
import Repository.RepositoryException;
import Service.OrderService;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class OrderServiceTest {

    @Test
    public void addOrder() throws DuplicateEntityException, IOException, RepositoryException {
        IRepository<Order> repository = new MemoryRepository<>();
        OrderService orderService = new OrderService(repository);
        orderService.addOrder(1, null, "12-09-23");
        assert orderService.getAll().size() == 1;
    }

    @Test
    public void removeOrder() throws DuplicateEntityException, IOException, RepositoryException {
        IRepository<Order> repository = new MemoryRepository<>();
        OrderService orderService = new OrderService(repository);
        orderService.addOrder(1, null, "12-09-23");
        orderService.removeOrder(1);
        assert orderService.getAll().isEmpty();
    }

    @Test
    public void updateOrder() throws DuplicateEntityException, IOException, RepositoryException {
        IRepository<Order> repository = new MemoryRepository<>();
        OrderService orderService = new OrderService(repository);
        orderService.addOrder(1, null, "12-09-23");
        Order o = new Order(2, null, "12-09-23");
        orderService.updateOrder(0, o);
        ArrayList arrayList = (ArrayList) orderService.getAll();
        assert arrayList.get(0) == o;
    }

    @Test
    public void getAll() {
        IRepository<Order> repository = new MemoryRepository<>();
        OrderService orderService = new OrderService(repository);
        assert orderService.getAll().isEmpty();
    }
}