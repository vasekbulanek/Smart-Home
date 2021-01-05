package appliance;

import general.*;
import people.Person;

import java.util.HashMap;

public class Iron extends Appliance implements Tickable {
    private int electricityOn;
    private int electricityOff;
    private int water;
    private HashMap<String, String> eventLog;

    public Iron(House house, int electricityOn, int electricityOff, int water) {
        super(house);
        this.electricityOn = electricityOn;
        this.electricityOff = electricityOff;
        this.water = water;
        applianceType = Fasada.allClasses.iron;
        eventLog = new HashMap<>();
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
        eventLog.put("broken iron", null);
    }

    public void repair(Person person) {
        if (!functionality) {
            functionality = true;
            person.delay();
            eventLog.put("broken iron", person.getName());
        }
    }

    @Override
    public boolean use(Person person) {
        eventLog.put(person.getPersonType().toString()+" "+person.getName()+" is using iron", "activity");
        return super.use(person);
    }

    @Override
    public void report(Reporter reporter) {
        for (String key : eventLog.keySet()) {
            if (eventLog.get(key)!=null && !eventLog.get(key)
                    .equals("activity")) {
                reporter.eventCatch(key, eventLog.get(key));
            } else reporter.activityCatch(eventLog.toString(), key);
        }
        eventLog = new HashMap<>();
    }

}