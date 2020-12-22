package appliance;
import general.Room;
import general.Tickable;

public class Boiler extends Appliance implements Tickable {
    private int electricityOn;
    private int electricityOff;
    private int water;

    public Boiler(int electricityOn, int electricityOff, int water) {
        this.electricityOn = electricityOn;
        this.electricityOff = electricityOff;
        this.water = water;
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
