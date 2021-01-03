package appliance;

import general.House;


//TODO: Generate washing machines
public class WashingMachine extends Appliance{
    private int electricityOn;
    private int electricityOff;
    private int water;
    public WashingMachine(House house, int electricityOn, int electricityOff, int water) {
        super(house);
        this.electricityOn = electricityOn;
        this.electricityOff = electricityOff;
        this.water = water;
    }


    @Override
    public void use() {

    }

    @Override
    public void report() {

    }

    @Override
    public void tick() {

        if(this.on){
            this.usedElectricity += this.electricityOn;
            this.usedWater += this.water;
        }
        else {
            this.usedElectricity += this.electricityOff;
        }
    }
}
