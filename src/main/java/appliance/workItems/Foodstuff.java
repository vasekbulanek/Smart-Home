package appliance.workItems;

import appliance.Appliance;
import appliance.Stove;
import general.Fasada;
import general.House;
import people.Person;

public class Foodstuff implements Work {
    private Work currentState;
    House house;
    private int portions;

    private class Raw implements Work{

        @Override
        public boolean work() {
            currentState = new Cut();
            house.getPeopleFasada()
                    .getByType(Fasada.allClasses.mother)
                    .addWorkRequest(currentState);
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
        public boolean work() {
            house.getPeopleFasada().getByType(Fasada.allClasses.mother).addWorkRequest(this);
            return false;
        }

        @Override
        public boolean work(Appliance appliance, Person person) {
            if(appliance.getApplianceType()!= Fasada.allClasses.stove){
                person.addWorkRequest(this);
                return false;
            }
            appliance.use(person);
            currentState = new Prepared();
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

    private class Trash implements Work{

        @Override
        public boolean work() {
            currentState = new Raw();
            house.getPeopleFasada().getByType(Fasada.allClasses.mother).addWorkRequest(currentState);
            return true;
        }

        @Override
        public boolean work(Appliance appliance, Person person) {
            house.getPeopleFasada().getByType(Fasada.allClasses.mother).addWorkRequest(currentState);
            return false;
        }

        @Override
        public Fasada.allClasses need() {
            return null;
        }
    }

    public Foodstuff(House house) {
        this.house = house;
        currentState = new Raw();
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
                house.getPeopleFasada()
                     .getByType(Fasada.allClasses.mother)
                     .addWorkRequest(this);
            }
        }
    }

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
