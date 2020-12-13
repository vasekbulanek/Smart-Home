package appliance;
import general.Tickable;

public class Phone extends Appliance implements Tickable {
    public Phone(int electricityOn, int electricityOff) {
        this.electricityOn = electricityOn;
        this.electricityOff = electricityOff;
    }

    private int electricityOn;
    private int electricityOff;
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
