package Ui;

import Domain.Cake;
import Domain.Order;
import Repository.DuplicateEntityException;
import Repository.RepositoryException;
import Service.CakeService;
import Service.OrderService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Console {
    CakeService cakeService;
    OrderService orderService;
    Scanner scanner = new Scanner(System.in);
    public Console(CakeService cakeService, OrderService orderService) {
        this.cakeService = cakeService;
        this.orderService = orderService;
    }

    public void runMenu() throws DuplicateEntityException, IOException {
        while (true) {
            printMenu();
            String option = scanner.nextLine();
            switch (option) {
                case "1": {
                    System.out.println("Give an id: ");
                    int id = Integer.parseInt(String.valueOf(scanner.nextLine()));
                    System.out.println("Give a cake type: ");
                    String cakeType =scanner.nextLine();
                    this.addCake(id, cakeType);
                    break;
                }
                case "2": {
                    System.out.println("Give an id: ");
                    int id = Integer.parseInt(String.valueOf(scanner.nextLine()));
                    System.out.println("Give number of cakes:");
                    int nr = Integer.parseInt(String.valueOf(scanner.nextLine()));
                    ArrayList<Cake> tortsAux = new ArrayList<>();
                    while (nr > 0) {
                        System.out.println("Give cake id: ");
                        int idTort = Integer.parseInt(String.valueOf(scanner.nextLine()));
                        Cake t = cakeService.findById(idTort);
                        tortsAux.add(t);
                        nr--;
                    }

                    System.out.println("Give a date:");
                    String data = scanner.nextLine();
                    addOrder(id, tortsAux, data);
                    break;
                }
                case "3": {
                    this.showAllCakes();
                    break;
                }
                case "4": {
                    this.showAllOrders();
                    break;
                }
                case "5": {
                    System.out.println("Give the position of the cake: ");
                    int pos = Integer.parseInt(String.valueOf(scanner.nextLine()));
                    System.out.println("Give an new id: ");
                    int id = Integer.parseInt(String.valueOf(scanner.nextLine()));
                    System.out.println("Give a new cake type: ");
                    String cakeType =scanner.nextLine();
                    this.updateCake(pos, id, cakeType);
                    break;
                }
                case "6": {
                    System.out.println("Give the position of the cake: ");
                    int pos = Integer.parseInt(String.valueOf(scanner.nextLine()));
                    System.out.println("Give a new id: ");
                    int id = Integer.parseInt(String.valueOf(scanner.nextLine()));
                    System.out.println("Give a new number of cakes:");
                    int nr = Integer.parseInt(String.valueOf(scanner.nextLine()));
                    ArrayList<Cake> tortsAux = new ArrayList<>();
                    while (nr > 0) {
                        System.out.println("Give cake id: ");
                        int idTort = Integer.parseInt(String.valueOf(scanner.nextLine()));
                        Cake t = cakeService.findById(idTort);
                        tortsAux.add(t);
                        nr--;
                    }

                    System.out.println("Give a new date:");
                    String data = scanner.nextLine();
                    this.updateOrder(pos, id, tortsAux, data);
                    break;
                }
                case "7": {
                    System.out.println("Give the id of the cake that you want to delete: ");
                    int id = Integer.parseInt(String.valueOf(scanner.nextLine()));
                    this.deleteCake(id);
                    break;
                }
                case "8": {
                    System.out.println("Give the id of the order that you want to delete: ");
                    int id = Integer.parseInt(String.valueOf(scanner.nextLine()));
                    this.deleteOrder(id);
                    break;
                }
                case "9": {
                    System.out.println("Numarul de torturi comandate in fiecare zi");
                    orderService.showAllCakesOrdered();
                    break;
                }
                case "10": {
                    System.out.println("Numarul de torturi comandate in fiecare luna");
                    orderService.showAllCakesOrderedPerMonth();
                    break;
                }
                case "11": {
                    System.out.println("Cele mai comandate torturi");
                    orderService.MostOrderedCakes();
                    break;
                }
                case "x": {
                    return;
                }
                default: {
                    System.out.println("Wrong option! Try again");
                }
            }
        }
    }

    private void deleteOrder(int id) throws IOException {
        orderService.removeOrder(id);
    }

    private void showAllOrders() {
        Collection<Order> orders = orderService.getAll();
        for(Order o: orders) {
            System.out.println(o);
        }
    }
    private void deleteCake(int id) throws IOException {
        cakeService.removeCake(id);
    }
    private void updateCake(int pos, int id, String cakeType) throws IOException {
        Cake c = new Cake(id, cakeType);
        cakeService.updateCake(pos, c);
    }

    private void updateOrder(int pos, int id, ArrayList<Cake> tortsAux, String date) throws IOException {
        Order o = new Order(id, tortsAux, date);
        orderService.updateOrder(pos, o);
    }
    private void addOrder(int id, ArrayList<Cake> tortsAux, String data) {
        try {
            orderService.addOrder(id, tortsAux, data);
        }
        catch (DuplicateEntityException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    private void printMenu(){
        System.out.println("1. Add cake");
        System.out.println("2. Add Order");
        System.out.println("3. Show all cakes");
        System.out.println("4. Show all orders");
        System.out.println("5. Update cake");
        System.out.println("6. Update order");
        System.out.println("7. Delete cake");
        System.out.println("8. Delete order");
        System.out.println("9.Numarul de torturi comandate in fiecare zi");
        System.out.println("10.Numarul de torturi comandate in fiecare luna");
        System.out.println("11.Cele mai comandate torturi");
        System.out.println("x. Good night");
    }

    private void addCake(int id, String cakeType) {
        try {
            cakeService.addCake(id, cakeType);
        }
        catch (DuplicateEntityException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

    }

    private void showAllCakes() {
        Collection<Cake> cakes = cakeService.getAll();
        for(Cake c: cakes) {
            System.out.println(c);
        }
    }
}
