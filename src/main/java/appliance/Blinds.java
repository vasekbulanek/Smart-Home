package appliance;

import general.*;
import people.Person;

import java.util.HashMap;

public class Blinds extends Appliance implements Tickable {
    private int electricityOn;
    private int electricityOff;
    private HashMap<String, String> eventLog;

    public Blinds(House house, int electricityOn, int electricityOff) {
        super(house);
        house.getWeather()
             .addObserver(this);
        this.electricityOn = electricityOn;
        this.electricityOff = electricityOff;
        applianceType = Fasada.allClasses.blinds;
        eventLog = new HashMap<>();
    }

    public void use() {
        on = true;
    }

    @Override
    public void tick() {

        if (this.on) {
            this.usedElectricity += this.electricityOn;
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
        eventLog.put("broken blinds", null);
        System.out.println("blinds XX");
    }

    public void repair(Person person) {
        if (!functionality) {
            functionality = true;
            person.delay();
            eventLog.put("broken blinds", person.getName());
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

    @Override
    public void place(Room room) {
        this.room = room;
    }
}