package Repository;

import Domain.Cake;
import Domain.Order;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.*;

public class SQLOrdersRepository extends MemoryRepository<Order> {
    private String JDBC_URL = "jdbc:sqlite:A4.sqlite";
    Connection connection;
    public SQLOrdersRepository() throws SQLException {
        openConnection();
        createTable();
        initData();
        //removeAll();
    }

    public void openConnection() throws SQLException {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(JDBC_URL);
        if (connection == null || connection.isClosed()) {
            connection = dataSource.getConnection();
        }
    }
    public void createTable() {
        try (final Statement st = connection.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS orders(id int, cakeOrders varchar(500), date varchar(50));");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Order findById(int id) {
//        ArrayList<Cake> list = new ArrayList<>();
//        try(PreparedStatement st = connection.prepareStatement("SELECT * FROM cakes WHERE id = ?;")) {
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                int id1 = rs.getInt("id");
//                String type = rs.getString("type");
//                Cake c = new Cake(id1, type);
//                list.add(c);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }
    public void add(Order order) {
        try(PreparedStatement st = connection.prepareStatement("INSERT INTO orders VALUES (?, ?, ?);")) {
            st.setInt(1, order.getId());
            System.out.println(order.getCakeOrders());
            st.setString(2, order.getCakeOrders().toString());
            st.setString(3, order.getDate());
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void remove(int id) {
        try(PreparedStatement st = connection.prepareStatement("DELETE FROM orders WHERE id = ?;")) {
            st.setInt(1, id);
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int pos, Order order) {
        try(PreparedStatement st = connection.prepareStatement("UPDATE orders SET cakeOrders = ?, date = ? WHERE id = ?;")) {
            st.setString(1, order.getCakeOrders().toString());
            st.setString(2, order.getDate());
            st.setInt(3, order.getId());
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Order> getAll() {
        ArrayList<Order> orders = new ArrayList<>();
        try(PreparedStatement st = connection.prepareStatement("SELECT * FROM orders;")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String cakeOrders = rs.getString("cakeOrders");
                ArrayList<Cake> cakes = new ArrayList<>();
                String splitBySpace = cakeOrders.substring(1, cakeOrders.length() - 1);
                String[] splitted = splitBySpace.split(", ");
                if (!Objects.equals(splitted[0], "")) {
                    for (String s : splitted) {
                        String[] aux = s.split(",");
                        int id1 = Integer.parseInt(aux[0]);
                        String type = aux[1];
                        Cake c = new Cake(id1, type);
                        cakes.add(c);
                    }
                }
                String date = rs.getString("date");
                Order o = new Order(id, cakes, date);
                orders.add(o);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    public void initData() {
        try(PreparedStatement st = connection.prepareStatement("INSERT INTO orders VALUES (?, ?, ?);")) {
           PreparedStatement cakeSt = connection.prepareStatement("SELECT * FROM cakes WHERE id = ?;");
           for (int i = 1; i < 100; i++) {
               // setam un id pentru fiecare order
               st.setInt(1, i);
               Random rand = new Random();
                // setam un numar random de cakes pentru fiecare order
               int nrCakes = rand.nextInt(10);
               ArrayList<Cake> cakes = new ArrayList<>();
               for (int j = 0; j < nrCakes; j++) {
                    // setam un id random pentru fiecare cake
                    int rId = rand.nextInt(100);
                    cakeSt.setInt(1, rId);
                    ResultSet rs = cakeSt.executeQuery();
                    // daca id-ul exista in baza de date, il adaugam in lista de cakes
                   int id = rs.getInt("id");
                   String type = rs.getString("type");
                   Cake c = new Cake(id, type);
                   cakes.add(c);
                }
                // setam lista de cakes pentru fiecare order
                st.setString(2, cakes.toString());
                // setam o data random pentru fiecare order
                int day = rand.nextInt(30);
                int month = rand.nextInt(12);
                int year = 2023;
                String date = day + "." + month + "." + year;
                st.setString(3, date);
                st.executeUpdate();
           }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeAll() {
        try(PreparedStatement st = connection.prepareStatement("DELETE FROM orders;")) {
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showNumberOfCakesPerDay() {
        try(PreparedStatement st = connection.prepareStatement("SELECT date, cakeOrders FROM orders;")) {
            ResultSet rs = st.executeQuery();
            ArrayList<String> dates = new ArrayList<>();
            ArrayList<String> cakeOrders = new ArrayList<>();
            while (rs.next()) {
                String date = rs.getString("date");
                String cakeOrder = rs.getString("cakeOrders");
                dates.add(date);
                cakeOrders.add(cakeOrder);
            }
            ArrayList<Integer> numberOfCakes = new ArrayList<>();
            for (String s : cakeOrders) {
                String splitBySpace = s.substring(1, s.length() - 1);
                String[] splitted = splitBySpace.split(", ");
                numberOfCakes.add(splitted.length);
            }
            for (int i = 0; i < numberOfCakes.size(); i++) {
                for (int j = i + 1; j < numberOfCakes.size(); j++) {
                    if (numberOfCakes.get(i) < numberOfCakes.get(j)) {
                        int aux = numberOfCakes.get(i);
                        numberOfCakes.set(i, numberOfCakes.get(j));
                        numberOfCakes.set(j, aux);
                        String aux2 = dates.get(i);
                        dates.set(i, dates.get(j));
                        dates.set(j, aux2);
                    }
                }
            }
            for (int i = 0; i < numberOfCakes.size(); i++) {
                System.out.println(dates.get(i) + " " + numberOfCakes.get(i));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*Numărul de torturi comandate în fiecare lună a anului.
    Se vor afișa lunile anului, precum și numărul de torturi comandate în fiecare lună.
    Afișarea se va face în ordine descrescătoare a numărului de torturi comandate.
     */
    public void showNumberOfCakesPerMont() {
        try (PreparedStatement st = connection.prepareStatement("SELECT date, cakeOrders FROM orders;")) {
            ResultSet rs = st.executeQuery();
            Map<String, Integer> monthlyCakeOrders = new HashMap<>();

            while (rs.next()) {
                String date = rs.getString("date");
                String cakeOrder = rs.getString("cakeOrders");

                // Assuming date is in the format "d.M.yyyy"
                String[] dateParts = date.split("\\.");
                String month = dateParts[1] + "." + dateParts[2];

                // Split cake orders and count the number of cakes
                String splitBySpace = cakeOrder.substring(1, cakeOrder.length() - 1);
                String[] splitted = splitBySpace.split(", ");
                int numberOfCakes = splitted.length;

                // Aggregate the number of cakes per month
                monthlyCakeOrders.put(month, monthlyCakeOrders.getOrDefault(month, 0) + numberOfCakes);
            }

            // Sort the results based on the number of cakes
            List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(monthlyCakeOrders.entrySet());
            sortedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

            // Print the sorted results
            for (Map.Entry<String, Integer> entry : sortedList) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void MostOrderedCakes() {
        try (PreparedStatement st = connection.prepareStatement("SELECT cakeOrders FROM orders;")) {
            ResultSet rs = st.executeQuery();
            Map<String, Integer> cakeOrdersCount = new HashMap<>();

            while (rs.next()) {
                String cakeOrders = rs.getString("cakeOrders");

                // Extract cakes from the string
                String[] cakesArray = cakeOrders.split(",");

                for (String cake : cakesArray) {
                    // Extract cake name by removing non-alphabetic characters
                    String cakeName = cake.replaceAll("[^a-zA-Z]", "").trim();

                    // Count the cake
                    cakeOrdersCount.put(cakeName, cakeOrdersCount.getOrDefault(cakeName, 0) + 1);
                }
            }

            // Sort the results based on the number of orders (in descending order)
            List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(cakeOrdersCount.entrySet());
            sortedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

            // Print the sorted results
            for (Map.Entry<String, Integer> entry : sortedList) {
                String cakeName = entry.getKey();
                int numOrders = entry.getValue();

                System.out.println("Cake Name: " + cakeName);
                System.out.println("Number of Orders: " + numOrders);
                System.out.println(); // Add a separator between cake entries
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}