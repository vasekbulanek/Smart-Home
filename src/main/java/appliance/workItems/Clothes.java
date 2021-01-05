package appliance.workItems;

import appliance.Appliance;
import appliance.Iron;
import appliance.WashingMachine;
import general.Fasada;
import general.House;
import people.Person;

public class Clothes implements Work {
    private Work currentState;
    House house;

    private class Dirty implements Work{

        @Override
        public boolean work() {
            house.getPeopleFasada().getByType(Fasada.allClasses.mother).addWorkRequest(this);
            return false;
        }

        @Override
        public boolean work(Appliance appliance, Person person) {
            if(appliance.getApplianceType()!= Fasada.allClasses.washingmachine){
                house.getPeopleFasada().getByType(Fasada.allClasses.mother).addWorkRequest(this);
                return false;
            }
            appliance.use(person);
            currentState = new Washed();
            person.addWorkRequest(currentState);
            return true;
        }

        @Override
        public Fasada.allClasses need() {
            return Fasada.allClasses.washingmachine;
        }
    }

    private class Washed implements Work{

        @Override
        public boolean work() {
            house.getPeopleFasada().getByType(Fasada.allClasses.mother).addWorkRequest(this);
            return false;
        }

        @Override
        public boolean work(Appliance appliance, Person person) {
            if(appliance.getApplianceType()!= Fasada.allClasses.iron){
                house.getPeopleFasada().getByType(Fasada.allClasses.mother).addWorkRequest(this);
                return false;
            }
            appliance.use(person);
            currentState = new Ironed();
            return true;
        }

        @Override
        public Fasada.allClasses need() {
            return Fasada.allClasses.iron;
        }
    }

    private class Ironed implements Work{

        @Override
        public boolean work() {
            return false;
        }

        @Override
        public boolean work(Appliance appliance, Person person) {
            return false;
        }

        @Override
        public Fasada.allClasses need() {
            return null;
        }
    }

    public Clothes(House house) {
        this.house = house;
        currentState = new Ironed();
    }

    public void becomeDirty() {
        if (currentState.need() == null) {
            currentState = new Dirty();
            house.getPeopleFasada().getByType(Fasada.allClasses.mother).addWorkRequest(this);
        }
    }

    @Override
    public boolean work() {
        return currentState.work();
    }

    @Override
    public boolean work(Appliance appliance, Person person) {
        return currentState.work(appliance, person);
    }

    @Override
    public Fasada.allClasses need() {
        return currentState.need();
    }
}
