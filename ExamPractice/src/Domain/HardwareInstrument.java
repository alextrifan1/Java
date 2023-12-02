package Domain;

import java.util.Objects;

public class HardwareInstrument extends FlightInstrument
{
    private String type;
    public HardwareInstrument(String code, Boolean mt, String type) {
        super(code, mt);
        this.type = type;
    }
    @Override
    public String toString() {
        return "HardwareInstrument: " + "code: " +this.code + " maintenance: " + this.maintenance + " version: " + this.type;
    }

    @Override
    public double getPrice() {
        int price;
        if (Objects.equals(type, "altitudine") || Objects.equals(type, "directie"))
            price = 50;
        else
            price = 500;
        if (maintenance)
            return price*2;
        else
            return price;
    }
}
