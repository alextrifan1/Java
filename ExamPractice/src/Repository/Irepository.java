package Repository;

import Domain.FlightInstrument;

import java.util.Collection;

public interface Irepository<T extends FlightInstrument> extends Iterable<T> {
    void add(T instrument);
    Collection<T> getAll();
}
