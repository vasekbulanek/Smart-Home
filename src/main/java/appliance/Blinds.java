package appliance;

import general.Fasada;
import general.House;
import general.Room;
import general.Tickable;

public class Blinds extends Appliance implements Tickable{
    private int electricityOn;
    private int electricityOff;

    public Blinds(House house, int electricityOn, int electricityOff) {
        super(house);
        house.getWeather().addObserver(this);
        this.electricityOn = electricityOn;
        this.electricityOff = electricityOff;
        applianceType = Fasada.allClasses.blinds;
    }

    public void use(){
        on=true;
    }

    @Override
    public void tick() {

        if (this.on) {
            this.usedElectricity += this.electricityOn;
            on=false;
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