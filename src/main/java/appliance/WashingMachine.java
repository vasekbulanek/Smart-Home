package appliance;

import appliance.workItems.Clothes;
import general.Fasada;
import general.House;
import general.Reporter;
import people.Person;

import java.util.HashMap;
import java.util.Random;


//TODO: Generate washing machines
public class WashingMachine extends Appliance {
    private int electricityOn;
    private int electricityOff;
    private int water;
    private Clothes content;
    private HashMap<String, String> eventLog;

    public WashingMachine(House house, int electricityOn, int electricityOff, int water) {
        super(house);
        this.electricityOn = electricityOn;
        this.electricityOff = electricityOff;
        this.water = water;
        content = new Clothes(house);
        applianceType = Fasada.allClasses.washingmachine;
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
        Random random = new Random();
        if (random.nextInt(24) == 1){
            content.becomeDirty();
        }
    }

    protected void breakDown() {
        house.getPeopleFasada()
             .getByType(Fasada.allClasses.father)
             .addRepairableRequest(this);
        functionality = false;
        eventLog.put("broken washing machine", null);
    }

    public void repair(Person person) {
        if (!functionality) {
            functionality = true;
            person.delay();
            eventLog.put("broken washing machine", person.getName());
        }
    }

    @Override
    public void report(Reporter reporter) {
        for (String key : eventLog.keySet()) {
            if (!eventLog.isEmpty()){
                reporter.eventCatch(key, eventLog.get(key));
            }
        }
        eventLog.clear();
    }

}
