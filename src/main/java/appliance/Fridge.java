package appliance;
import java.util.LinkedList;

public class Fridge extends Appliance{
    private LinkedList<String> content;
    private int electricityOn;
    private int electricityOff;

    public Fridge(int electricityOn, int electricityOff) {
        this.electricityOn = electricityOn;
        this.electricityOff = electricityOff;
        content=new LinkedList<>();
    }

    @Override
    public void use() {

    }

    public void report() {

    }

    public void tick() {

    }
}
