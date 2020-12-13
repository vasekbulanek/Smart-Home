package appliance;
import general.Tickable;

public class Blinds extends Appliance implements Tickable {
    private int electricityOn;
    private int electricityOff;

    public Blinds(int electricityOn, int electricityOff) {
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