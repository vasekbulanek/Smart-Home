package animals;

import general.*;
import people.Person;

import java.util.HashMap;
import java.util.Random;

public class Cat extends Animal {
    private HashMap<String, String> eventLog;

    public Cat(House house, String name) {
        super(house, name);
        animalType = Fasada.allClasses.cat;
        eventLog = new HashMap<>();
    }

    @Override
    public void tick() {
        hunger++;
        if (hunger > 12) {
            for (Person p : room.getPeople()) {
                p.addAnimalRequest(this);
                eventLog.put("feeding cat", null);
            }
        }
        Random random = new Random();
        int number = random.nextInt(house.getRoomFasada()
                                         .getRoomLinkedList()
                                         .size());
        Room r = house.getRoomFasada()
                      .getRoomLinkedList()
                      .get(number);
        r.addPropriet(this, room);
    }

    @Override
    public void report(Reporter reporter) {
        for (String key : eventLog.keySet()) {
            if (!eventLog.isEmpty()){
                reporter.eventCatch(key, eventLog.get(key));
            }
        }
        eventLog.clear();
    }

    @Override
    public void play(Person person) {
        eventLog.put(person.getName() + " is playing with cat " + name, "activity");
    }

    @Override
    public void feed(Person person) {
        if (room != person.getRoom()) person.getRoom()
                                            .addPropriet(this, room);
        super.feed(person);
        eventLog.put("feeding cat", person.getPersonType()
                                       .toString() + " " + person.getName());
    }

}

