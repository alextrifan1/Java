package Ui;

import Service.HardwareService;
import Service.SoftwareService;

import java.util.Scanner;

public class Console {
    HardwareService hardwareService;
    SoftwareService softwareService;
    Scanner scanner = new Scanner(System.in);
    public Console(HardwareService hardwareService, SoftwareService softwareService) {
        this.hardwareService = hardwareService;
        this.softwareService = softwareService;
    }
    private void printMenu() {
        System.out.println("1. Adauga instrument");
        System.out.println("2. Afiseaza toate instrumentele");
        System.out.println("3. Afiseaza instrumentele sub un anume pret");
        System.out.println("x. EXIT");
    }
    public void runMenu() {
        while (true){
            printMenu();
            String option = scanner.nextLine();
            switch (option) {
                case "1": {
                    System.out.println("Alege tipul de instrument(1 Soft/ 2 Hard)");
                    int tipInstrument = Integer.parseInt(String.valueOf(scanner.nextLine()));
                    if (tipInstrument == 1) {
                        System.out.println("Alege cod: ");
                        String code = scanner.nextLine();
                        System.out.println("Alege daca este in mentenanta: ");
                        Boolean mt = Boolean.parseBoolean(scanner.nextLine());
                        System.out.println("Alege versiunea: ");
                        int version = Integer.parseInt(String.valueOf(scanner.nextLine()));
                        softwareService.addSoftwareInstrument(code, mt, version);
                    }
                    else {
                        System.out.println("Alege cod: ");
                        String code = scanner.nextLine();
                        System.out.println("Alege daca este in mentenanta(true/false): ");
                        Boolean mt = Boolean.parseBoolean(scanner.nextLine());
                        System.out.println("Alege tipul: ");
                        String type = scanner.nextLine();
                        hardwareService.addHardwareInstrument(code, mt, type);
                    }
                    break;
                }
                case "2": {
                    break;
                }
                case "3": {
                    break;
                }
                case "x": {
                    return;
                }
                default:{
                    System.out.println("optiune gresita");
                }
            }
        }
    }

}
