package appliance;
import general.Room;
import general.Tickable;

public class Stove extends Appliance implements Tickable {
    private int electricityOn;
    private int electricityOff;

    public Stove(int electricityOn, int electricityOff) {
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
