package appliance.workItems;

import appliance.Appliance;
import general.Fasada;
import general.House;
import general.Reporter;
import people.Person;

import java.util.HashMap;

public class Foodstuff implements Work {
    private Work currentState;
    House house;
    private int portions;
    private HashMap<String, String>eventLog;

    private class Raw implements Work{

        @Override
        public boolean work(Person person) {
            currentState = new Cut();
            eventLog.put("cook meal", null);
            house.getPeopleFasada().getPeopleIterator().next().addWorkRequest(currentState);
            eventLog.put("cut foodstuff", person.getPersonType().toString()+" "+person.getName());
            return true;
        }

        @Override
        public boolean work(Appliance appliance, Person person) {
            person.addWorkRequest(this);
            return false;
        }

        @Override
        public Fasada.allClasses need() {
            return null;
        }
    }

    private class Cut implements Work{

        @Override
        public boolean work(Person person) {
            house.getPeopleFasada().getPeopleIterator().next().addWorkRequest(this);
            return false;
        }

        @Override
        public boolean work(Appliance appliance, Person person) {
            if(appliance==null)return false;
            if(appliance.getApplianceType()!= Fasada.allClasses.stove){
                house.getPeopleFasada().getPeopleIterator().next().addWorkRequest(this);
                return false;
            }
            appliance.use(person);
            currentState = new Prepared();
            eventLog.put("cook meal", person.getPersonType().toString()+" "+person.getName());
            portions = house.getPeopleFasada()
                    .getSize() * 2;
            return true;
        }

        @Override
        public Fasada.allClasses need() {
            return Fasada.allClasses.stove;
        }
    }

    private class Prepared implements Work{
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

    private class Trash implements Work{

        @Override
        public boolean work(Person person) {
            currentState = new Raw();
            house.getPeopleFasada().getByType(Fasada.allClasses.mother).addWorkRequest(currentState);
            eventLog.put("take out waste and buy food", person.getPersonType().toString()+" "+person.getName());
            eventLog.put("cut foodstuff", null);
            return true;
        }

        @Override
        public boolean work(Appliance appliance, Person person) {
            house.getPeopleFasada().getPeopleIterator().next().addWorkRequest(this);
            return false;
        }

        @Override
        public Fasada.allClasses need() {
            return null;
        }
    }

    public Foodstuff(House house) {
        this.house = house;
        eventLog = new HashMap<>();
        currentState = new Raw();
        eventLog.put("cut foodstuff", null);
        portions = 0;
        house.getPeopleFasada()
                .getByType(Fasada.allClasses.mother)
                .addWorkRequest(this);
    }

    public void beEaten(Person person) {
        if (portions>0) {
            portions--;
            person.eat(this);
            if (portions == 0) {
                currentState = new Trash();
                eventLog.put("take out waste and buy food", null);
                house.getPeopleFasada()
                        .getByType(Fasada.allClasses.mother)
                        .addWorkRequest(this);
            }
        }
    }

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

