package people;

import animals.Animal;
import appliance.workItems.Work;
import general.Fasada;
import general.House;
import general.Repairable;
import general.Reporter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Baby extends Person {
    private boolean diaper;
    private Map<String, String> diary;

    public Baby(House house, String name) {
        super(house, name);
        diaper = false;
        personType = Fasada.allClasses.baby;
        diary = new HashMap<>();
    }

    private void hungry() {
        hunger++;
        if (hunger > 6 && activity != longActivity.sleep) {
            house.getPeopleFasada()
                 .getByType(Fasada.allClasses.mother)
                 .addPersonRequest(this);
        } else if (hunger > 8) {
            activity = longActivity.no;
            house.getPeopleFasada()
                 .getByType(Fasada.allClasses.mother)
                 .addPersonRequest(this);
            diary.put("feeding baby", null);
        }
    }

    private void poop() {
        if (diaper && activity != longActivity.sleep) {
            house.getPeopleFasada()
                 .getByType(Fasada.allClasses.mother)
                 .addPersonRequest(this);
            diary.put("diapering baby", null);
            return;
        }
        Random random = new Random();
        int number = random.nextInt(5);
        if (hunger <= 6 && number == 1) {
            diaper = true;
        }
    }

    protected void diapering(Person person) {
        diary.put("diapering baby", person.getPersonType()
                                          .toString() +
                " " + person.getName());
        diaper = false;
    }

    protected void eating(Person person) {
        diary.put("feeding baby", person.getPersonType()
                                        .toString() + " " + person.getName());
        hunger = 0;
    }

    public boolean goSleep() {
        if (hunger < 4) {
            Random random = new Random();
            int number = random.nextInt(3);
            if (number == 1) {
                activity = longActivity.sleep;
                diary.put(personType.toString()+" "+name+" sleeps", "activity");
                return true;
            }
        }
        return false;
    }

    @Override
    public void tick() {
        house.getPeopleFasada()
             .getByType(Fasada.allClasses.mother)
             .addPersonRequest(this);
        poop();
        hungry();
        goSleep();
    }

    @Override
    public void report(Reporter reporter) {
        for (String key : diary.keySet()) {
            if (diary.get(key)==null || !diary.get(key)
                    .equals("activity")) {
                reporter.eventCatch(key, diary.get(key));
            } else reporter.activityCatch(personType.toString() + " " + name, key);
        }
        diary = new HashMap<>();
    }


    public int getHunger() {
        return hunger;
    }

    public boolean getDiaper() {
        return diaper;
    }

    public void addPersonRequest(Person person) {
        house.getPeopleFasada()
             .getByType(Fasada.allClasses.mother)
             .addPersonRequest(person);
        diary.put(personType.toString()+" "+name+" passed personrequest to mother", "activity");
    }

    public void addAnimalRequest(Animal animal) {
        request.addAnimal(animal);
        house.getPeopleFasada()
             .getByType(Fasada.allClasses.mother)
             .addAnimalRequest(animal);
        diary.put(personType.toString()+" "+name+" passed animalrequest to mother", "activity");
    }

    public void addRepairableRequest(Repairable repairable) {
        house.getPeopleFasada()
             .getByType(Fasada.allClasses.mother)
             .addRepairableRequest(repairable);
        diary.put(personType.toString()+" "+name+" passed repairable to mother", "activity");
    }

    public void addWorkRequest(Work work) {
        house.getPeopleFasada()
             .getByType(Fasada.allClasses.father)
             .addWorkRequest(work);
        diary.put(personType.toString()+" "+name+" passed work to mother", "activity");
    }

    @Override
    protected void wakeUp() {
        super.wakeUp();
        diary.put(personType.toString()+" "+name+" wakes up", "activity");
    }
}
