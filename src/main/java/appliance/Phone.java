package appliance;

import general.House;
import general.Room;
import general.Tickable;

public class Phone extends Appliance implements Tickable {
    public Phone(House house, int electricityOn, int electricityOff) {
        super(house);
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

        if(this.on){
            this.usedElectricity += this.electricityOn;
        }
        else {
            this.usedElectricity += this.electricityOff;
        }
    }

    @Override
    public void report() {

    }

}
