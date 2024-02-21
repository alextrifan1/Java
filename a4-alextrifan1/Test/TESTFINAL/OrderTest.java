package TESTFINAL;

import Domain.Cake;
import Domain.Order;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class OrderTest {

    @Test
    public void setId() {
        Cake c = new Cake(1, "vanilie");
        ArrayList<Cake> arrayList = new ArrayList<>();
        arrayList.add(c);
        Order o = new Order(1, arrayList,"12-09-23");
        o.setId(2);
        assert o.getId() == 2;
    }

    @Test
    public void getId() {
        Cake c = new Cake(1, "vanilie");
        ArrayList<Cake> arrayList = new ArrayList<>();
        arrayList.add(c);
        Order o = new Order(1, arrayList,"12-09-23");
        assert o.getId() == 1;
    }

    @Test
    public void getCakeOrders() {
        Cake c = new Cake(1, "vanilie");
        ArrayList<Cake> arrayList = new ArrayList<>();
        arrayList.add(c);
        Order o = new Order(1, arrayList,"12-09-23");
        assert o.getCakeOrders() == arrayList;
    }

    @Test
    public void getDate() {
        Cake c = new Cake(1, "vanilie");
        ArrayList<Cake> arrayList = new ArrayList<>();
        arrayList.add(c);
        Order o = new Order(1, arrayList,"12-09-23");
        assert o.getDate().equals("12-09-23");
    }

    @Test
    public void setCakeOrders() {
        Cake c = new Cake(1, "vanilie");
        ArrayList<Cake> arrayList = new ArrayList<>();
        arrayList.add(c);
        Order o = new Order(1, arrayList,"12-09-23");
        ArrayList<Cake> arrayList2 = new ArrayList<>();
        arrayList2.add(c);
        o.setCakeOrders(arrayList2);
        assert o.getCakeOrders() == arrayList2;
    }

    @Test
    public void setDate() {
        Cake c = new Cake(1, "vanilie");
        ArrayList<Cake> arrayList = new ArrayList<>();
        arrayList.add(c);
        Order o = new Order(1, arrayList,"12-09-23");
        o.setDate("13-10-24");
        assert o.getDate().equals("13-10-24");
    }

    @Test
    public void testToString() {
        Cake c = new Cake(1, "vanilie");
        ArrayList<Cake> arrayList = new ArrayList<>();
        arrayList.add(c);
        Order o = new Order(1, arrayList,"12-09-23");
        assert o.toString().equals("1,1,vanilie ,12-09-23");
    }
}