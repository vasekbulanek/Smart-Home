package appliance;

import appliance.workItems.Foodstuff;
import general.Fasada;
import general.House;
import general.Reporter;
import people.Person;

import java.util.HashMap;

public class Fridge extends Appliance {
    private Foodstuff content;
    private int electricityOn;
    private int electricityOff;
    private HashMap<String, String> eventLog;

    public Fridge(House house, int electricityOn, int electricityOff) {
        super(house);
        this.electricityOn = electricityOn;
        this.electricityOff = electricityOff;
        content = new Foodstuff(house);
        applianceType = Fasada.allClasses.fridge;
        eventLog = new HashMap<>();
    }

    public void tick() {

        if (this.on) {
            this.usedElectricity += this.electricityOn;
        } else {
            this.usedElectricity += this.electricityOff;
        }
    }

    protected void breakDown() {
        house.getPeopleFasada()
             .getByType(Fasada.allClasses.father)
             .addRepairableRequest(this);
        functionality = false;
        eventLog.put("broken fridge", null);
    }

    public void repair(Person person) {
        if (!functionality) {
            functionality = true;
            person.delay();
            eventLog.put("broken fridge", person.getName());
        }
    }

    public void report(Reporter reporter) {

    }

    public Foodstuff getContent() {
        return content;
    }
}
