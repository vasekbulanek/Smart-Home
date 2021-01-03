package appliance;

import general.House;
import general.Room;
import general.Tickable;

public class Blinds extends Appliance implements Tickable {
    private int electricityOn;
    private int electricityOff;

    public Blinds(House house, int electricityOn, int electricityOff) {
        super(house);
        this.electricityOn = electricityOn;
        this.electricityOff = electricityOff;
    }

    @Override
    public void use() {

    }

    @Override
    public void tick() {

        if (this.on) {
            this.usedElectricity += this.electricityOn;
        } else {
            this.usedElectricity += this.electricityOff;
        }
    }

    @Override
    public void report() {

    }

    @Override
    public void place(Room room) {
        this.room = room;
    }
}