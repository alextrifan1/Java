package Repository;

import Domain.Cake;
import org.sqlite.SQLiteDataSource;

import javax.print.attribute.standard.Finishings;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class SQLCakesRepository extends MemoryRepository<Cake>{
    private String JDBC_URL = "jdbc:sqlite:A4.sqlite";

    Connection connection;

    public SQLCakesRepository() throws SQLException {
        openConnection();
        createTable();
        //initData();
    }

    private void openConnection() throws SQLException {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(JDBC_URL);

        if (connection == null || connection.isClosed()) {
            connection = dataSource.getConnection();
        }
    }
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void createTable() throws SQLException {
        try (final Statement st = connection.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS cakes(id int, type varchar(20));");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void initData() {
        try(PreparedStatement st = connection.prepareStatement("INSERT INTO cakes VALUES (?, ?);")) {
            String[] types = {"Chocolate", "Fruit", "Cheese", "Cupcake", "Icecream", "Carrot", "Red Velvet", "Pound", "Sponge", "Chiffon"};
            String[] types2 = {"Chocolate", "Fruit", "Cheese", "Cupcake", "Icecream", "Carrot", "Red Velvet", "Pound", "Sponge", "Chiffon"};
            for (int i = 4; i < 105; i++) {
                String type = types[(int)(Math.random() * 10)];
                String type2 = types2[(int)(Math.random() * 10)];
                String result = type + type2;
                st.setInt(1, i);
                st.setString(2, result);
                st.executeUpdate();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Cake> getAll() {
        ArrayList<Cake> cakes = new ArrayList<>();
        try(PreparedStatement st = connection.prepareStatement("SELECT * FROM cakes;")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                Cake c = new Cake(id, type);
                cakes.add(c);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return cakes;
    }
    public void add(Cake c) {
        try(PreparedStatement st = connection.prepareStatement("INSERT INTO cakes VALUES (?, ?);")) {
            st.setInt(1, c.getId());
            st.setString(2, c.getcakeType());
            st.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(int pos, Cake c) {
        try(PreparedStatement st = connection.prepareStatement("UPDATE cakes SET type = ? WHERE id = ?;")) {
            st.setString(1, c.getcakeType());
            st.setInt(2, c.getId());
            st.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Cake findById(int id) {
        try(PreparedStatement st = connection.prepareStatement("SELECT * FROM cakes WHERE id = ?;")) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id1 = rs.getInt("id");
                String type = rs.getString("type");
                Cake c = new Cake(id1, type);
                return c;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void remove(int id) {
        try(PreparedStatement st = connection.prepareStatement("DELETE FROM cakes WHERE id = ?;")) {
            st.setInt(1, id);
            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
