package Service;

import Domain.HardwareInstrument;
import Repository.Irepository;

import java.util.Collection;

public class HardwareService {
    Irepository<HardwareInstrument> repo;
    public HardwareService(Irepository<HardwareInstrument> repo) {
        this.repo = repo;
    }
    public void addHardwareInstrument(String code, Boolean mt, String type) {
        repo.add(new HardwareInstrument(code, mt, type));
    }
    public Collection<HardwareInstrument> getAll() {
        return repo.getAll();
    }
}
