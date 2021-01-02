package people;

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
        if(request.allRequests()>0){
            if(request.hasTo(Request.Typ.person)>0){
                Person a =request.getPerson();
                solvePerson(a);}

        }
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
}