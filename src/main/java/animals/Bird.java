package animals;

import general.*;
import people.Person;

import java.util.HashMap;

public class Bird extends Animal {
    private HashMap<String, String>diary;

    public Bird(House house, String name) {
        super(house, name);
        animalType = Fasada.allClasses.bird;
        diary =new HashMap<>();
    }

    @Override
    public void tick() {
        hunger++;
        if(hunger>12){
            for (Person p: room.getPeople()) {
                p.addAnimalRequest(this);
            }
            diary.put("feeding bird", null);
        }

    }

    @Override
    public void report(Reporter reporter) {
        for (String key:diary.keySet()) {
            if(!diary.get(key).equals("activity")) {
                reporter.eventSolved(key, diary.get(key));
            }
            else reporter.activityCatch(animalType.toString() + " " + name, key);
        }
        diary = new HashMap<>();
    }


    @Override
    public void play(Person person) {
        diary.put(person.getName()+" is talking with bird "+ name, "activity");
    }

    @Override
    protected void feed(Person person) {
        super.feed(person);
        diary.put("feeding bird", person.getPersonType().toString()+ " " + person.getName());
    }
}
