package animals;

import general.*;
import people.Person;

import java.util.HashMap;

public class Dog extends Animal {
    private Person master;
    private boolean validMaster;
    private Room allowed;
    private HashMap<String, String> eventLog;

    public Dog(House house, String name) {
        super(house, name);
        validMaster = false;
        animalType = Fasada.allClasses.dog;
        eventLog = new HashMap<>();
    }

    @Override
    public void tick() {
        hunger++;
        if (hunger > 12) {
            if (master == null || hunger > 24) {
                for (Person p : room.getPeople()) {
                    p.addAnimalRequest(this);
                }
                eventLog.put("feeding dog", null);
                validMaster = false;
            }
            else {
                master.addAnimalRequest(this);
                eventLog.put("feeding dog", null);
            }
        }
        if (master != null && master.getRoom() == house.getRoomFasada()
                                                       .getOutside()) {
            house.getRoomFasada()
                 .getOutside()
                 .addPropriet(this, room);
            eventLog.put("go with " + master.getName() + " out", "activity");
        } else if (allowed != null) {
            allowed.addPropriet(this, room);
        }
    }

    @Override
    public void feed(Person person) {
        if (room != person.getRoom()) person.getRoom()
                                            .addPropriet(this, room);
        super.feed(person);
        if (!validMaster) {
            master = person;
            eventLog.put("my new master is " + master.getName(), "activity");
        }
        eventLog.put("feeding dog", person.getPersonType()
                                       .toString() + " " + person.getName());
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
        eventLog.put(person.getName() + " is playing with dog " + name, "activity");
    }

    public void place(Room room) {
        if (allowed == null) allowed = room;
        this.room = room;
    }
}