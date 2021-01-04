package appliance;

import appliance.workItems.Clothes;
import general.Fasada;
import general.House;
import people.Person;

import java.util.Random;


//TODO: Generate washing machines
public class WashingMachine extends Appliance {
    private int electricityOn;
    private int electricityOff;
    private int water;
    private Clothes content;

    public WashingMachine(House house, int electricityOn, int electricityOff, int water) {
        super(house);
        this.electricityOn = electricityOn;
        this.electricityOff = electricityOff;
        this.water = water;
        content = new Clothes(house);
        applianceType = Fasada.allClasses.washingMachine;
    }

    @Override
    public void report() {

    }

    @Override
    public void tick() {
        if (this.on) {
            this.usedElectricity += this.electricityOn;
            this.usedWater += this.water;
            on=false;
        } else {
            this.usedElectricity += this.electricityOff;
        }
        Random random = new Random();
        if(random.nextInt(24)==1)content.becomeDirty();
    }
}
