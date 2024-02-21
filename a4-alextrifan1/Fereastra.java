import Domain.Cake;
import Domain.Order;
import Repository.*;
import Service.CakeService;
import Service.OrderService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Fereastra extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        IRepository<Cake> cakeIRepository = new MemoryRepository<>();
        CakeService cakeService = new CakeService(cakeIRepository);

        IRepository<Order> orderIRepository = new MemoryRepository<>();
        OrderService orderService = new OrderService(orderIRepository);

        HBox root = new HBox();

        VBox cakeVBox = new VBox();
        VBox orderVBox = new VBox();

        cakeVBox.setPrefWidth(520);
        orderVBox.setPrefWidth(520);
        cakeVBox.setPadding(new javafx.geometry.Insets(10));
        orderVBox.setPadding(new javafx.geometry.Insets(10));

        root.getChildren().add(cakeVBox);
        root.getChildren().add(orderVBox);

        Scene scene = new Scene(root, 1080, 720);
        
        ListView<Cake> listView = new ListView<>();
        ObservableList<Cake> items = FXCollections.observableArrayList(cakeService.getAll());
        listView.setItems(items);

        GridPane cakeGridPane = new GridPane();
        javafx.scene.control.Label idLabel = new javafx.scene.control.Label("ID");
        javafx.scene.control.TextField cakeIDTextField = new javafx.scene.control.TextField();

        javafx.scene.control.Label typeLabel = new javafx.scene.control.Label("Type");
        javafx.scene.control.TextField cakeTypeTextField = new javafx.scene.control.TextField();
        
        cakeGridPane.add(idLabel, 0, 0);
        cakeGridPane.add(cakeIDTextField, 1, 0);
        cakeGridPane.add(typeLabel, 0, 1);
        cakeGridPane.add(cakeTypeTextField, 1, 1);

        cakeVBox.setPadding(new javafx.geometry.Insets(10));

        HBox hbox = new HBox();
        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button clearButton = new Button("Clear");
        hbox.getChildren().add(addButton);
        hbox.getChildren().add(updateButton);
        hbox.getChildren().add(deleteButton);
        hbox.getChildren().add(clearButton);
        
        addButton.setOnMouseClicked(mouseEvent -> {
            try {
                cakeService.addCake(Integer.parseInt(cakeIDTextField.getText()), cakeTypeTextField.getText());
                items.setAll(cakeService.getAll());
                cakeIDTextField.setText("");
                cakeTypeTextField.setText("");
            } catch (DuplicateEntityException e) {
                throw new RuntimeException(e);
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        listView.setOnMouseClicked(mouseEvent -> {
            Cake cake = listView.getSelectionModel().getSelectedItem();
            cakeIDTextField.setText(String.valueOf(cake.getId()));
            cakeTypeTextField.setText(cake.getcakeType());
        });

        updateButton.setOnMouseClicked(mouseEvent -> {
            int id = Integer.parseInt(cakeIDTextField.getText());
            String type = cakeTypeTextField.getText();
            Cake c = new Cake(id, type);
            try {
                cakeService.updateCake(id, c);
                items.setAll(cakeService.getAll());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        deleteButton.setOnMouseClicked(mouseEvent -> {
            int id = Integer.parseInt(cakeIDTextField.getText());
            try {
                cakeService.removeCake(Integer.parseInt(cakeIDTextField.getText()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            items.setAll(cakeService.getAll());
        });

        clearButton.setOnMouseClicked(mouseEvent -> {
            cakeIDTextField.setText("");
            cakeTypeTextField.setText("");

        });

        ListView<Order> listView2 = new ListView<>();
        ObservableList<Order> items2 = FXCollections.observableArrayList(orderService.getAll());
        listView2.setItems(items2);
        
        ///// order
        GridPane orderGridPane = new GridPane();
        
        javafx.scene.control.Label idLabel2 = new javafx.scene.control.Label("ID");
        javafx.scene.control.TextField orderIDTextField = new javafx.scene.control.TextField();
        
        javafx.scene.control.Label cakeLabel = new javafx.scene.control.Label("Cakes");
        javafx.scene.control.TextField cakeTextField = new javafx.scene.control.TextField();
        
        javafx.scene.control.Label dataLabel = new javafx.scene.control.Label("Date (d.m.yyyy)");
        javafx.scene.control.TextField orderDataTextField = new javafx.scene.control.TextField();
        
        javafx.scene.control.Label porpouseLabel = new javafx.scene.control.Label("Porpouse");
        javafx.scene.control.TextField orderScopTextField = new javafx.scene.control.TextField();

        orderGridPane.add(idLabel2, 0, 0);
        orderGridPane.add(orderIDTextField, 1, 0);
        orderGridPane.add(cakeLabel, 0, 1);
        orderGridPane.add(cakeTextField, 1, 1);
        orderGridPane.add(dataLabel, 0, 2);
        orderGridPane.add(orderDataTextField, 1, 2);


        HBox hbox2 = new HBox();
        Button addButton2 = new Button("Add");
        Button updateButton2 = new Button("Update");
        Button deleteButton2 = new Button("Delete");
        Button clearButton2 = new Button("Clear");
        hbox2.getChildren().add(addButton2);
        hbox2.getChildren().add(updateButton2);
        hbox2.getChildren().add(deleteButton2);
        hbox2.getChildren().add(clearButton2);


        listView2.setOnMouseClicked(mouseEvent -> {
            Order order = listView2.getSelectionModel().getSelectedItem();
            orderIDTextField.setText(String.valueOf(order.getId()));
            cakeTextField.setText(order.getCakeOrders().toString());
            orderDataTextField.setText(order.getDate());
        });

        addButton2.setOnMouseClicked(mouseEvent -> {
            try {
                String cakes = cakeTextField.getText();
                String[] cakesArray = cakes.split(",");
                ArrayList<Cake> tortsAux = new ArrayList<>();
                for (String s : cakesArray) {
                    tortsAux.add(cakeService.findById(Integer.parseInt(s)));
                }
                orderService.addOrder(Integer.parseInt(orderIDTextField.getText()), tortsAux, orderDataTextField.getText());
                items2.setAll(orderService.getAll());

            } catch (Exception e) {
                throw new RuntimeException(e);
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
        });

        updateButton2.setOnMouseClicked(mouseEvent -> {
            int selectedIndex = listView2.getSelectionModel().getSelectedIndex();
            String cakes = cakeTextField.getText();

            int pos = Integer.parseInt(orderIDTextField.getText());

            String[] cakesArray = cakes.split(",");
            ArrayList<Cake> tortsAux = new ArrayList<>();
            String date = orderDataTextField.getText();

            Order newOrder = new Order(pos, tortsAux, date);
            for (String s : cakesArray) {
                tortsAux.add(cakeService.findById(Integer.parseInt(s)));
            }
            try {
                orderService.updateOrder(pos, newOrder);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            items2.setAll(orderService.getAll());
        });

        deleteButton2.setOnMouseClicked(mouseEvent -> {
            try {
                orderService.removeOrder(Integer.parseInt(orderIDTextField.getText()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            items2.setAll(orderService.getAll());
        });

        clearButton2.setOnMouseClicked(mouseEvent -> {
            orderIDTextField.setText("");
            cakeTextField.setText("");
            orderDataTextField.setText("");
        });

        cakeVBox.getChildren().add(listView);
        cakeVBox.getChildren().add(cakeGridPane);
        cakeVBox.getChildren().add(hbox);
        orderVBox.getChildren().add(listView2);
        orderVBox.getChildren().add(orderGridPane);
        orderVBox.getChildren().add(hbox2);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
