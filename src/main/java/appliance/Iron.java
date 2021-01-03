package appliance;

import general.House;
import general.Room;
import general.Tickable;

public class Iron extends Appliance implements Tickable {
    private int electricityOn;
    private int electricityOff;
    private int water;

    public Iron(House house, int electricityOn, int electricityOff, int water) {
        super(house);
        this.electricityOn = electricityOn;
        this.electricityOff = electricityOff;
        this.water = water;
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