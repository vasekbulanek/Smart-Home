package animals;

import general.House;
import general.Room;
import general.Tickable;
import people.Person;

public class Dog extends Animal{

    public Dog(House house) {
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