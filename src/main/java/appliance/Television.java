package appliance;

import general.Room;
import general.Tickable;

public class Television extends Appliance implements Tickable {
    private int electricityOn;
    private int electricityOff;

    public Television(int electricityOn, int electricityOff) {
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

    @Override
    public void place(Room room) {

    }
}
