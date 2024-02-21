package TESTFINAL;

import Domain.Cake;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class CakeTest {

    @Test
    public void setId() {
        Cake c = new Cake(1, "Vanilie");
        c.setId(2);
        Assert.assertEquals(c.getId(), 2);
    }

    @Test
    public void getId() {
        Cake c = new Cake(1, "Vanilie");
        assert c.getId() == 1;
    }

    @Test
    public void setcakeType() {
        Cake c = new Cake(1, "Vanilie");
        c.setcakeType("BBB");
        assert Objects.equals(c.getcakeType(), "BBB");
    }

    @Test
    public void getcakeType() {
        Cake c = new Cake(1, "Vanilie");
        assert Objects.equals(c.getcakeType(), "Vanilie");
    }

    @Test
    public void testToString() {
        Cake c = new Cake(1, "Vanilie");
        assert c.toString().equals("1,Vanilie");
    }
}