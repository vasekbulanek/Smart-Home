package animals;

import general.House;
import general.Room;
import general.Tickable;
import people.Person;

import java.util.Random;

public class Cat extends Animal  {

    public Cat(House house) {
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
        Random random = new Random();
        int number = random.nextInt(house.getRoomFasada().getRoomLinkedList().size()) ;
        Room r = house.getRoomFasada().getRoomLinkedList().get(number);
        r.addPropriet(this);
    }

    @Override
    public void report() {

    }


    @Override
    public void play() {

    }

}

