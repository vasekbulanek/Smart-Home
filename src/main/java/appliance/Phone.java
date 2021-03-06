package appliance;

import general.*;
import people.Person;

import java.util.HashMap;

public class Phone extends Appliance implements Tickable {
    private int electricityOn;
    private int electricityOff;
    private HashMap<String, String> eventLog;

    public Phone(House house, int electricityOn, int electricityOff) {
        super(house);
        this.electricityOn = electricityOn;
        this.electricityOff = electricityOff;
        applianceType = Fasada.allClasses.phone;
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
        eventLog.put("broken phone", null);
    }

    public void repair(Person person) {
        if (!functionality) {
            functionality = true;
            person.delay();
            eventLog.put("broken phone", person.getName());
        }
    }

    @Override
    public boolean use(Person person) {
        eventLog.put(person.getPersonType().toString()+" "+person.getName()+" is using phone", "activity");
        return super.use(person);
    }

    @Override
    public void report(Reporter reporter) {
        for (String key : eventLog.keySet()) {
            if (eventLog.get(key)==null || !eventLog.get(key)
                    .equals("activity")) {
                reporter.eventCatch(key, eventLog.get(key));
            } else reporter.activityCatch(eventLog.toString(), key);
        }
        eventLog = new HashMap<>();
    }
}
