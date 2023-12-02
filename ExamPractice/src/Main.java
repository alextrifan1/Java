import Domain.HardwareInstrument;
import Domain.SoftwareInstrument;
import Repository.Irepository;
import Repository.MRepository;

public class Main {
    public static void main(String[] args) {
        Irepository<SoftwareInstrument> softwareInstrumentIrepository = new MRepository<>();
        Irepository<HardwareInstrument> hardwareInstrumentIrepository = new MRepository<>();

        Service.SoftwareService softwareService = new Service.SoftwareService(softwareInstrumentIrepository);
        Service.HardwareService hardwareService = new Service.HardwareService(hardwareInstrumentIrepository);

        hardwareService.addHardwareInstrument("code1", true, "altitudine");
        hardwareService.addHardwareInstrument("code2", false, "altitudine");
        softwareService.addSoftwareInstrument("code3", true, 1);
        softwareService.addSoftwareInstrument("code4", false, 11);

        Ui.Console console = new Ui.Console(hardwareService, softwareService);
        console.runMenu();
    }
}
