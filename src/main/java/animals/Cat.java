package animals;

import general.*;
import people.Person;

import java.util.HashMap;
import java.util.Random;

public class Cat extends Animal {
    private HashMap<String, String> diary;

    public Cat(House house, String name) {
        super(house, name);
        animalType = Fasada.allClasses.cat;
        diary = new HashMap<>();
    }

    @Override
    public void tick() {
        hunger++;
        if (hunger > 12) {
            for (Person p : room.getPeople()) {
                p.addAnimalRequest(this);
                diary.put("feeding cat", null);
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
        for (String key : diary.keySet()) {
            if (diary.get(key)==null || !diary.get(key)
                      .equals("activity")) {
                reporter.eventCatch(key, diary.get(key));
            } else reporter.activityCatch(animalType.toString() + " " + name, key);
        }
        diary = new HashMap<>();
    }

    @Override
    public void play(Person person) {
        diary.put(person.getPersonType().toString()+" "+person.getName() + " is playing with cat " + name, "activity");
    }

    @Override
    public void feed(Person person) {
        if (room != person.getRoom()) person.getRoom()
                                            .addPropriet(this, room);
        super.feed(person);
        diary.put("feeding cat", person.getPersonType()
                                       .toString() + " " + person.getName());
    }

}

