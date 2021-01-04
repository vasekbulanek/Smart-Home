package appliance;

import general.*;

public class Boiler extends Appliance implements Tickable {
    private int electricityOn;
    private int electricityOff;
    private int water;

    public Boiler(House house, int electricityOn, int electricityOff, int water) {
        super(house);
        this.electricityOn = electricityOn;
        this.electricityOff = electricityOff;
        this.water = water;
        applianceType = Fasada.allClasses.boiler;
    }

    @Override
    public void tick() {

        if (this.on) {
            this.usedElectricity += this.electricityOn;
            this.usedWater += this.water;
            on = false;
        } else {
            this.usedElectricity += this.electricityOff;
        }
    }

    @Override
    public void report(Reporter reporter) {

    }

}
