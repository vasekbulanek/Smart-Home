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
    public void use() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void report() {

    }

}