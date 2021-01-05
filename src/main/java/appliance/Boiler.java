package appliance;

import general.*;
import people.Person;

import java.util.HashMap;

public class Boiler extends Appliance implements Tickable {
    private int electricityOn;
    private int electricityOff;
    private int water;
    private HashMap<String, String> eventLog;

    public Boiler(House house, int electricityOn, int electricityOff, int water) {
        super(house);
        this.electricityOn = electricityOn;
        this.electricityOff = electricityOff;
        this.water = water;
        applianceType = Fasada.allClasses.boiler;
        eventLog = new HashMap<>();
    }

    @Override
    public void tick() {

        if (this.on) {
            this.usedElectricity += this.electricityOn;
            this.usedWater += this.water;
            on = false;
        } else {
            this.usedElectricity += this.electricityOff;
        }
    }

    protected void breakDown() {
        house.getPeopleFasada()
             .getByType(Fasada.allClasses.father)
             .addRepairableRequest(this);
        functionality = false;
        eventLog.put("broken boiler", null);
    }

    public void repair(Person person) {
        if (!functionality) {
            functionality = true;
            person.delay();
            eventLog.put("broken boiler", person.getName());
        }
    }

    @Override
    public void report(Reporter reporter) {

    }

}
