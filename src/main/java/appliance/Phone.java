package appliance;

import general.*;

public class Phone extends Appliance implements Tickable {
    public Phone(House house, int electricityOn, int electricityOff) {
        super(house);
        this.electricityOn = electricityOn;
        this.electricityOff = electricityOff;
        applianceType = Fasada.allClasses.phone;
    }

    private int electricityOn;
    private int electricityOff;

    @Override
    public void tick() {

        if (this.on) {
            this.usedElectricity += this.electricityOn;
            on = false;
        } else {
            this.usedElectricity += this.electricityOff;
        }
    }

    @Override
    public void report(Reporter reporter) {

    }

}
