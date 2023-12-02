package Domain;

public class SoftwareInstrument extends FlightInstrument{
    private int version;
    public SoftwareInstrument(String code, Boolean mt, int version) {
        super(code, mt);
        this.version = version;
    }
    @Override
    public String toString() {
        return "SoftwareInstrument: " + "code: " +this.code + " maintenance: " + this.maintenance + " version: " + this.version;
    }

    @Override
    public double getPrice() {
        int price;
        if (version < 10)
            price = 100;
        else price = 200;
        if (maintenance)
            return price*2;
        else
            return price;
    }
}
