package animals;

import general.House;
import general.Room;
import general.Tickable;
import people.Person;

import java.util.LinkedList;

public class Bird extends Animal {

    public Bird(House house) {
        super(house);
    }

    @Override
    public void tick() {
        hunger++;
        if(hunger>12){
            for (Person p: room.getPeople()) {
                p.addAnimalRequest(this);
            }
        }

    }


    @Override
    public void report() {

    }


    @Override
    public void play() {

    }
}
