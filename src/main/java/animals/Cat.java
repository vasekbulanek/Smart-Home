package animals;

import general.Fasada;
import general.House;
import general.Room;
import general.Tickable;
import people.Person;

import java.util.Random;

public class Cat extends Animal  {

    public Cat(House house, String name) {
        super(house, name);
        animalType = Fasada.allClasses.cat;
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
        r.addPropriet(this, room);
    }

    @Override
    public void report() {

    }


    @Override
    public void play() {

    }
    @Override
    public void feed(Person person) {
        if(room!=person.getRoom())person.getRoom().addPropriet(this, room);
        super.feed(person);

    }

}

