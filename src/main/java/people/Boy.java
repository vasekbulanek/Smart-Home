package people;

import animals.Animal;
import general.Fasada;
import general.House;
import general.Reporter;

import java.util.HashMap;

public class Boy extends Person {
    private HashMap<String, String> diary;

    public Boy(House house, String name) {
        super(house, name);
        personType = Fasada.allClasses.boy;
        diary = new HashMap<>();
    }


    @Override
    public void tick() {
        super.tick();
        if (request.allRequests() > 0) {
            if (request.hasTo(Request.Typ.repairable) > 0) {
                request.getRepairable()
                       .repair(this);
                return;
            }
            if (request.hasTo(Request.Typ.work) > 0) {
                workSolve();
                return;
            }
            if (request.hasTo(Request.Typ.animal) > 0) {
                Animal animal = request.getAnimal();
                animal.getRoom()
                      .addPropriet(this, room);
                if (animal.getHunger() > 10) animal.feed();
                animal.play(this);
            }

        } else sport();
    }

    @Override
    public void report(Reporter reporter) {
        for (String key : diary.keySet()) {
            if (diary.get(key)!=null && !diary.get(key)
                      .equals("activity")) {
                reporter.eventCatch(key, diary.get(key));
            } else reporter.activityCatch(personType.toString() + " " + name, key);
        }
        diary = new HashMap<>();
    }

    public void addPersonRequest(Person person) {
        Mother mother = (Mother) house.getPeopleFasada()
                                      .getByType(Fasada.allClasses.mother);
        if (mother != null) {
            diary.put(personType.toString()+" "+name+"passed person request to mother", "activity");
            mother.addPersonRequest(person);
        } else {
            Girl girl = (Girl) house.getPeopleFasada()
                                    .getByType(Fasada.allClasses.girl);
            if (girl != null) {
                diary.put(personType.toString()+" "+name+" passed person request to sister", "activity");
                girl.addPersonRequest(person);
            }
        }
    }

    @Override
    protected void sleep() {
        super.sleep();
        diary.put(personType.toString()+" "+name+" sleeps", "activity");
    }

    @Override
    protected void wakeUp() {
        super.wakeUp();
        diary.put(personType.toString()+" "+name+" wakes up", "activity");
    }
}

