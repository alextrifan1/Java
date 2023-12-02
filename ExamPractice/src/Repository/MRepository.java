package Repository;

import Domain.FlightInstrument;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MRepository<T extends FlightInstrument> implements Irepository<T>{
    List<T> instruments = new ArrayList<>();
    @Override
    public void add(T instrument) {
        instruments.add(instrument);
    }

    @Override
    public Collection<T> getAll() {
        return new ArrayList<>(instruments);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayList<T>(instruments).iterator();
    }
}
