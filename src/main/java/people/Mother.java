package people;

import animals.Animal;
import appliance.Appliance;
import appliance.Boiler;
import general.House;
import general.Room;
import general.Tickable;
import people.Person;

public class Mother extends Person {
    private static Mother singleton = null;

    private Mother(House house) {
        super(house);
    }


    public static Mother getInstance(House house) {
        if (singleton == null) {
            singleton = new Mother(house);
            return singleton;
        } else {
            System.out.println("This house already has its mother. No new will be created.");
            return singleton;
        }
    }

    @Override
    public void tick() {
        if(activity!=longActivity.no){
            if(activity==longActivity.sleep){
                if(request.hasTo(Request.Typ.person)>0) {
                    wakeUp();
                    solvePerson(request.getPerson());
                    if (house.getPeopleFasada().getByType("Baby").activity != longActivity.sleep) {
                        ((Baby)house.getPeopleFasada().getByType("Baby")).Gosleep();
                    }
                }
                sleep();
                return;
            }
            if (activity == longActivity.sport && using!=null){
                using.Tidy();
                using=null;
                Boiler boiler =(Boiler) house.getApplianceFasada().getByType("Boiler");
                if(boiler!=null)boiler.use();
            }
            activity=longActivity.no;
            return;
        }
        if(request.allRequests()>0){
            while(request.allRequests()>1 && request.hasTo(Request.Typ.work)>0){
                house.getPeopleFasada().getRandom().addWorkRequest(request.getWork());
            }
            if(request.hasTo(Request.Typ.person)>0){
                Person a =request.getPerson();
                solvePerson(a);
            return;}
            if(request.hasTo(Request.Typ.repairable)>1){
                request.getRepairable().repair(this);
                if(house.getPeopleFasada().getByType("Father")!=null){
                    house.getPeopleFasada().getByType("Father").addRepairableRequest(request.getRepairable());
                }
                return;
            }
            if(request.hasTo(Request.Typ.work)>0){
                workSolve();
                return;
            }

        }
        sport();
    }

    @Override
    public void report() {

    }

    @Override
    protected void useAppliance() {

    }
    void solvePerson(Person person){
        if(person instanceof Baby)solvePerson((Baby) person);
        if(person instanceof Boy)solvePerson((Boy) person);
        if(person instanceof Girl)solvePerson((Girl) person);
        if(person instanceof Father)solvePerson((Father) person);
        if(person instanceof Mother)solvePerson((Mother) person);
    }
    void solvePerson(Baby baby){
        baby.getRoom().addPropriet(this, null);
        if(baby.getHunger()>4)baby.eating();
        if (baby.getDiaper())baby.diapering();
    }
    void solvePerson(Girl girl){

    }
    void solvePerson(Father father){

    }
    void solvePerson(Boy boy){

    }
    void solvePerson(Mother mother){

    }

    public void addAnimalRequest(Animal animal) {
        house.getPeopleFasada().getRandom().addAnimalRequest(animal);
    }
}