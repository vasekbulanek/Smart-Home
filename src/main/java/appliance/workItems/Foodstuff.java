package appliance.workItems;

import appliance.Appliance;
import appliance.Stove;
import general.Fasada;
import general.House;
import people.Person;

public class Foodstuff implements Work {
    private stateFood currentState;
    House house;
    private int portions;

    private enum stateFood {
        raw, cut, prepared, trash
    }

    public Foodstuff(House house) {
        this.house = house;
        currentState = stateFood.raw;
        portions = 0;
        house.getPeopleFasada()
             .getByType(Fasada.allClasses.mother)
             .addWorkRequest(this);
    }

    public void beEaten(Person person) {
        if (currentState == stateFood.prepared) {
            portions--;
            person.eat(this);
            if (portions == 0) {
                currentState = stateFood.trash;
                house.getPeopleFasada()
                     .getByType(Fasada.allClasses.mother)
                     .addWorkRequest(this);
            }
        }
    }

    public boolean work() {
        if (currentState == stateFood.raw) {
            currentState = stateFood.cut;
            house.getPeopleFasada()
                 .getByType(Fasada.allClasses.mother)
                 .addWorkRequest(this);
            return true;
        }
        if (currentState == stateFood.trash) {
            currentState = stateFood.raw;
            house.getPeopleFasada()
                 .getByType(Fasada.allClasses.mother)
                 .addWorkRequest(this);
            return true;
        }
        return false;
    }

    @Override
    public boolean work(Appliance appliance, Person person) {
        if (appliance.toString()
                     .contains("Stove.")) {
            return work((Stove) appliance, person);
        }
        return false;
    }

    @Override
    public Fasada.allClasses need() {
        if (currentState == stateFood.cut) return Fasada.allClasses.stove;
        return null;
    }

    public boolean work(Stove stove, Person person) {
        stove.use(person);
        if (currentState == stateFood.cut) {
            currentState = stateFood.prepared;
            portions = house.getPeopleFasada()
                            .getSize() * 2;
            return true;
        }
        person.addWorkRequest(this);
        return false;
    }

    public stateFood getCurrentState() {
        return currentState;
    }
}
