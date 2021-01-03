package appliance;

import general.House;
import general.Room;

import java.util.LinkedList;

public class Fridge extends Appliance {
    private LinkedList<String> content;
    private int electricityOn;
    private int electricityOff;

    public Fridge(House house, int electricityOn, int electricityOff) {
        super(house);
        this.electricityOn = electricityOn;
        this.electricityOff = electricityOff;
        content = new LinkedList<>();
    }

    @Override
    public void use() {

    }

    public void report() {

    }

    public void tick() {

        if(this.on){
            this.usedElectricity += this.electricityOn;
        }
        else {
            this.usedElectricity += this.electricityOff;
        }
    }
}
