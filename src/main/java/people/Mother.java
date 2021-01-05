package people;

import animals.Animal;
import appliance.Boiler;
import general.Fasada;
import general.House;
import general.Reporter;

import java.util.HashMap;

public class Mother extends Person {
    private static Mother singleton = null;
    private HashMap<String, String> diary;

    private Mother(House house, String name) {
        super(house, name);
        personType = Fasada.allClasses.mother;
        diary = new HashMap<>();
    }


    public static Mother getInstance(House house, String name) {
        if (singleton == null) {
            singleton = new Mother(house, name);
            return singleton;
        } else {
            System.out.println("This house already has its mother. No new will be created.");
            return singleton;
        }
    }

    @Override
    public void tick() {
        if (activity != longActivity.no) {
            if (activity == longActivity.sleep) {
                if (request.hasTo(Request.Typ.person) > 0) {
                    wakeUp();
                    solvePerson(request.getPerson());
                    if (house.getPeopleFasada()
                             .getByType(Fasada.allClasses.baby).activity != longActivity.sleep) {
                        ((Baby) house.getPeopleFasada()
                                     .getByType(Fasada.allClasses.baby)).Gosleep();
                    }
                }
                sleep();
                return;
            }
            if (activity == longActivity.sport && using != null) {
                using.Tidy();
                using = null;
                Boiler boiler = (Boiler) house.getApplianceFasada()
                                              .getByType(Fasada.allClasses.boiler);
                if (boiler != null) boiler.use(this);
                else house.getRoomFasada()
                          .getRoomLinkedList()
                          .get(0)
                          .addPropriet(this, room);
            }
            activity = longActivity.no;
            return;
        }
        if (request.allRequests() > 0) {
            while (request.allRequests() > 1 && request.hasTo(Request.Typ.work) > 0) {
                house.getPeopleFasada()
                     .getRandom()
                     .addWorkRequest(request.getWork());
            }
            if (request.hasTo(Request.Typ.person) > 0) {
                Person a = request.getPerson();
                solvePerson(a);
                return;
            }
            if (request.hasTo(Request.Typ.repairable) > 1) {
                request.getRepairable()
                       .repair(this);
                if (house.getPeopleFasada()
                         .getByType(Fasada.allClasses.father) != null) {
                    house.getPeopleFasada()
                         .getByType(Fasada.allClasses.father)
                         .addRepairableRequest(request.getRepairable());
                }
                return;
            }
            if (request.hasTo(Request.Typ.work) > 0) {
                workSolve();
                return;
            }

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
    }

    void solvePerson(Person person) {
        if (person instanceof Baby) solvePerson((Baby) person);
        if (person instanceof Boy) solvePerson((Boy) person);
        if (person instanceof Girl) solvePerson((Girl) person);
        if (person instanceof Father) solvePerson((Father) person);
        if (person instanceof Mother) solvePerson((Mother) person);
    }

    void solvePerson(Baby baby) {
        room.addPropriet(baby, baby.getRoom());
        if (baby.getHunger() > 4) baby.eating(this);
        if (baby.getDiaper()) baby.diapering(this);
    }

    void solvePerson(Girl girl) {

    }

    void solvePerson(Father father) {

    }

    void solvePerson(Boy boy) {

    }

    void solvePerson(Mother mother) {

    }

    public void addAnimalRequest(Animal animal) {
        house.getPeopleFasada()
             .getRandom()
             .addAnimalRequest(animal);
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