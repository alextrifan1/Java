import Domain.*;
import Repository.*;
import Service.CakeService;
import Service.OrderService;
//import Test.CakeTest;
//import Test.OrderTest;
import Ui.Console;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.Provider;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws DuplicateEntityException, IOException, ClassNotFoundException, RepositoryException, SQLException {



        Settings setari = Settings.getInstance();

        if (Objects.equals(setari.getRepoType(), "interfatagrafica")) {
            Application.launch(Fereastra.class);
        }


        if (Objects.equals(setari.getRepoType(), "memory")) {
            IRepository<Cake> cakeRepository = new MemoryRepository<>();
            IRepository<Order> orderRepository = new MemoryRepository<>();
            CakeService cakeService = new CakeService(cakeRepository);
            OrderService orderService = new OrderService(orderRepository);
            Console console = new Console(cakeService, orderService);
            console.runMenu();
        }

        if (Objects.equals(setari.getRepoType(), "text/binar")) {
            IEntityFactory<Cake> cakeFactory = new CakeFactory();
            IEntityFactory<Order> orderFactory = new OrderFactory();
            IRepository<Cake> cakeRepository = new BinaryFileRepository<>(setari.getRepoFile1());
            IRepository<Order>  orderRepository = new BinaryFileRepository<>(setari.getRepoFile2());
            CakeService cakeService = new CakeService(cakeRepository);
            OrderService orderService = new OrderService(orderRepository);
            Console console = new Console(cakeService, orderService);
            console.runMenu();
        }
        if (Objects.equals(setari.getRepoType(), "database")) {
            SQLCakesRepository cakeDbRepository = new SQLCakesRepository();
            SQLOrdersRepository orderDbRepository = new SQLOrdersRepository();
            CakeService cakeService = new CakeService(cakeDbRepository);
            OrderService orderService = new OrderService(orderDbRepository);
            Console console = new Console(cakeService, orderService);
            console.runMenu();
            cakeDbRepository.closeConnection();
            orderDbRepository.closeConnection();
        }

  }
}
