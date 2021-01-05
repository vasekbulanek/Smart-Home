package appliance.workItems;

import appliance.Appliance;
import general.Fasada;
import general.House;
import general.Reporter;
import people.Person;

import java.util.HashMap;

public class Clothes implements Work {
    private Work currentState;
    House house;
    private HashMap<String, String>eventLog;

    private class Dirty implements Work{

        @Override
        public boolean work(Person person) {
            house.getPeopleFasada().getByType(Fasada.allClasses.mother).addWorkRequest(this);
            eventLog.put("wash clothes", null);
            return false;
        }

        @Override
        public boolean work(Appliance appliance, Person person) {
            if(appliance.getApplianceType()!= Fasada.allClasses.washingmachine){
                house.getPeopleFasada().getByType(Fasada.allClasses.mother).addWorkRequest(this);
                return false;
            }
            appliance.use(person);
            eventLog.put("wash clothes", person.getPersonType()+" "+person.getName());
            currentState = new Washed();
            person.addWorkRequest(currentState);
            eventLog.put("iron clothes", null);
            return true;
        }

        @Override
        public Fasada.allClasses need() {
            return Fasada.allClasses.washingmachine;
        }
    }

    private class Washed implements Work{

        @Override
        public boolean work(Person person) {
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
            eventLog.put("iron clothes", person.getPersonType()+" "+person.getName());
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
        public boolean work(Person person) {
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
        eventLog = new HashMap<>();
        currentState = new Ironed();
    }

    public void becomeDirty() {
        if (currentState.need() == null) {
            currentState = new Dirty();
            house.getPeopleFasada().getByType(Fasada.allClasses.mother).addWorkRequest(this);
        }
    }

    @Override
    public boolean work(Person person) {
        return currentState.work(person);
    }

    @Override
    public boolean work(Appliance appliance, Person person) {
        return currentState.work(appliance, person);
    }

    @Override
    public Fasada.allClasses need() {
        return currentState.need();
    }

    public void report(Reporter reporter) {
        for (String key : eventLog.keySet()) {
            if (!eventLog.isEmpty()){
                reporter.eventCatch(key, eventLog.get(key));
            }
        }
        eventLog.clear();
    }
}
