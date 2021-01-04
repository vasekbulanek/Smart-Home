package appliance;

import general.*;

public class Television extends Appliance {
    private int electricityOn;
    private int electricityOff;

    public Television(House house, int electricityOn, int electricityOff) {
        super(house);
        this.electricityOn = electricityOn;
        this.electricityOff = electricityOff;
        applianceType = Fasada.allClasses.stove;
    }

    @Override
    public void tick() {

        if (this.on) {
            this.usedElectricity += this.electricityOn;
            on=false;
        } else {
            this.usedElectricity += this.electricityOff;
        }
    }

    @Override
    public void report(Reporter reporter) {

    }

}
