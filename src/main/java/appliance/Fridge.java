package appliance;

import appliance.workItems.Foodstuff;
import general.Fasada;
import general.House;
import general.Reporter;

public class Fridge extends Appliance {
    private Foodstuff content;
    private int electricityOn;
    private int electricityOff;

    public Fridge(House house, int electricityOn, int electricityOff) {
        super(house);
        this.electricityOn = electricityOn;
        this.electricityOff = electricityOff;
        content = new Foodstuff(house);
        applianceType = Fasada.allClasses.fridge;
    }

    public void report(Reporter reporter) {

    }

    public void tick() {

        if (this.on) {
            this.usedElectricity += this.electricityOn;
        } else {
            this.usedElectricity += this.electricityOff;
        }
    }

    public Foodstuff getContent() {
        return content;
    }
}
