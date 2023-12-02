package Domain;

public abstract class FlightInstrument {
    protected String code;
    protected boolean maintenance;

    FlightInstrument(String code, boolean maintenance){
        this.code = code;
        this.maintenance=maintenance;
    }

    public abstract String toString();

    public abstract double getPrice();
}
