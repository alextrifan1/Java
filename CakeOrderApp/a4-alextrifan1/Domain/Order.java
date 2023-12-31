package Domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Order extends Entity implements Serializable {
    private ArrayList<Cake> cakeOrders;
    private String date;
    public Order(int id, ArrayList<Cake> cakeOrders, String date) {
        super(id);
        this.cakeOrders = cakeOrders;
        this.date = date;
    }
    public ArrayList<Cake> getCakeOrders() {
        return this.cakeOrders;
    }
    public String getDate() {
        return this.date;
    }
    public void setCakeOrders(ArrayList<Cake> cakeOrders) {
        this.cakeOrders = cakeOrders;
    }
    public void setDate(String date) {
        this.date = date;
    }
    @Override
    public String toString() {
         String finalOrder = String.valueOf(this.id) + ",";
         for (Cake c: cakeOrders) {
             finalOrder += c.toString() + " ";
         }
         finalOrder += ","+date;
         return finalOrder;
    }
}
