package Service;

import Domain.HardwareInstrument;
import Domain.SoftwareInstrument;
import Repository.Irepository;

import java.util.Collection;

public class SoftwareService {
    Irepository<SoftwareInstrument> repo;
    public SoftwareService(Irepository<SoftwareInstrument> repo) {
        this.repo = repo;
    }
    public void addSoftwareInstrument(String code, Boolean mt, int version) {
        repo.add(new SoftwareInstrument(code, mt, version));
    }
    public Collection<SoftwareInstrument> getAll() {
        return repo.getAll();
    }
}
