package appliance;

import general.House;
import general.Room;
import general.Tickable;

public class Stove extends Appliance implements Tickable {
    private int electricityOn;
    private int electricityOff;

    public Stove(House house, int electricityOn, int electricityOff) {
        super(house);
        this.electricityOn = electricityOn;
        this.electricityOff = electricityOff;
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
    public void report() {

    }

}