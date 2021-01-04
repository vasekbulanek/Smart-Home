package animals;

import general.*;
import people.Person;

public class Bird extends Animal {

    public Bird(House house, String name) {
        super(house, name);
        animalType = Fasada.allClasses.bird;
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
    public void report(Reporter reporter) {

    }


    @Override
    public void play() {

    }
}
