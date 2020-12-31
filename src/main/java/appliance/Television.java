package appliance;

import appliance.Appliance;
import general.House;
import general.Room;
import general.Tickable;

public class Television extends Appliance  {
    private int electricityOn;
    private int electricityOff;

    public Television(House house, int electricityOn, int electricityOff) {
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
