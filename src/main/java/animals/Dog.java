package animals;

import general.House;
import general.Room;
import general.Tickable;
import people.Person;

public class Dog extends Animal{
    Person master;
    boolean validMaster;
    public Dog(House house) {
        super(house);
        validMaster=false;
    }

    @Override
    public void tick() {
        hunger++;
        if(hunger>12){
            if(master==null || hunger>24) {
                for (Person p : room.getPeople()) {
                    p.addAnimalRequest(this);
                }
                validMaster=false;
            }
            master.addAnimalRequest(this);
        }

    }

    @Override
    public void feed(Person person) {
        if(room!=person.getRoom())person.getRoom().addPropriet(this, room);
        super.feed(person);
        if(!validMaster)master=person;

    }

    @Override
    public void report() {

    }

    @Override
    public void play() {

    }
}