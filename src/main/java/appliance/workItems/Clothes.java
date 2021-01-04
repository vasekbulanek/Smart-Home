package appliance.workItems;

import appliance.Appliance;
import appliance.Iron;
import appliance.WashingMachine;
import general.Fasada;
import general.House;
import people.Person;

public class Clothes implements Work {
    private stateCloth currentState;
    House house;

    private enum stateCloth {
        dirty, washed, ironed
    }

    public Clothes(House house) {
        this.house = house;
        currentState = stateCloth.washed;
    }

    public void becomeDirty() {
        if (currentState == stateCloth.ironed) {
            currentState = stateCloth.dirty;
        }
    }

    @Override
    public boolean work() {
        return false;
    }

    @Override
    public boolean work(Appliance appliance, Person person) {
        if (appliance.getApplianceType() == Fasada.allClasses.washingMachine) {
            return work((WashingMachine) appliance, person);
        }
        if (appliance.getApplianceType() == Fasada.allClasses.iron) {
            return work((Iron) appliance, person);
        }
        return false;
    }

    @Override
    public Fasada.allClasses need() {
        if (currentState == stateCloth.dirty) return Fasada.allClasses.washingMachine;
        if (currentState == stateCloth.washed) return Fasada.allClasses.iron;
        return null;
    }

    public boolean work(Iron iron, Person person) {
        iron.use(person);
        if (currentState == stateCloth.washed) {
            currentState = stateCloth.ironed;
            return true;
        }
        person.addWorkRequest(this);
        return false;
    }

    public boolean work(WashingMachine washingMachine, Person person) {
        washingMachine.use(person);
        if (currentState == stateCloth.dirty) {
            currentState = stateCloth.washed;
            return true;
        }
        person.addWorkRequest(this);
        return false;
    }
}
