package Domain;

import java.io.Serializable;

public class Cake extends Entity implements Serializable {
    private String cakeType;
    public Cake(int id, String cakeType) {
        super(id);
        this.cakeType = cakeType;
    }
    public void setcakeType(String cakeType) {
        this.cakeType = cakeType;
    }
    public String getcakeType() {
        return this.cakeType;
    }
    @Override
    public String toString() {
        return this.id + "," + this.cakeType;
    }
}
