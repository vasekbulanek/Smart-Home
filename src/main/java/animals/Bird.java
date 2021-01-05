package animals;

import general.*;
import people.Person;

import java.util.HashMap;

public class Bird extends Animal {
    private HashMap<String, String> eventLog;

    public Bird(House house, String name) {
        super(house, name);
        animalType = Fasada.allClasses.bird;
        eventLog = new HashMap<>();
    }

    @Override
    public void tick() {
        hunger++;
        if (hunger > 12) {
            for (Person p : room.getPeople()) {
                p.addAnimalRequest(this);
            }
            eventLog.put("feeding bird", null);
        }

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
        eventLog.put(person.getName() + " is talking with bird " + name, "activity");
    }

    @Override
    protected void feed(Person person) {
        super.feed(person);
        eventLog.put("feeding bird", person.getPersonType()
                                        .toString() + " " + person.getName());
    }
}
