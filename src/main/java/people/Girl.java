package people;

import animals.Animal;
import appliance.Phone;
import appliance.Television;
import appliance.workItems.Work;
import general.*;

import java.util.HashMap;
import java.util.Random;

public class Girl extends Person {
    private HashMap<String, String> diary;

    public Girl(House house, String name) {
        super(house, name);
        personType = Fasada.allClasses.girl;
        diary = new HashMap<>();
    }

    @Override
    public void tick() {
        super.tick();
        if (request.allRequests() > 0) {
            if (request.hasTo(Request.Typ.work) > 0) {
                Work r = request.getWork();
                Fasada.allClasses name = r.need();
                if (name != null) r.work(house.getApplianceFasada()
                                              .getByType(name), this);
                else r.work(this);
                return;
            }
            if (request.hasTo(Request.Typ.person) > 0) {
                solvePerson(request.getPerson());
                return;
            }
            if (request.hasTo(Request.Typ.animal) > 0) {
                Animal animal = request.getAnimal();
                animal.getRoom()
                      .addPropriet(this, room);
                if (animal.getHunger() > 10) animal.feed();
                animal.play(this);
                delay();
                return;
            }
        }
        Random random = new Random();
        if (random.nextBoolean()) {
            Phone phone = (Phone) house.getApplianceFasada()
                                       .getByType(Fasada.allClasses.phone);
            if (phone != null) phone.use(this);
            else {
                Television television = (Television) house.getApplianceFasada()
                                                          .getByType(Fasada.allClasses.television);
                if (television != null) television.use(this);
            }
            return;
        }
        sport();
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


    public void addRepairableRequest(Repairable repairable) {
        Father father = (Father) house.getPeopleFasada()
                                      .getByType(Fasada.allClasses.father);
        if (father != null) {
            father.addRepairableRequest(repairable);
            diary.put("passed repairable to father", "activity");
        } else {
            Mother mother = (Mother) house.getPeopleFasada()
                                          .getByType(Fasada.allClasses.mother);
            if (mother != null) {
                mother.addRepairableRequest(repairable);
                diary.put("passed repairable to mother", "activity");
            }
        }
    }

    private void solvePerson(Person person) {
        if (person instanceof Baby) solvePerson((Baby) person);
    }

    private void solvePerson(Baby baby) {
        baby.getRoom()
            .addPropriet(this, null);
        if (baby.getHunger() > 4) baby.eating(this);
        if (baby.getDiaper()) baby.diapering(this);
    }

    @Override
    protected void sleep() {
        super.sleep();
        diary.put("sleeps", "activity");
    }

    @Override
    protected void wakeUp() {
        super.wakeUp();
        diary.put("wakes up", "activity");
    }
}