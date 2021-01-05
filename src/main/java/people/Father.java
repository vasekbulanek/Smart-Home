package people;

import appliance.Appliance;
import general.Fasada;
import general.House;
import general.Reporter;

import java.util.HashMap;
import java.util.Random;


public class Father extends Person {
    private static Father singleton = null;
    private HashMap<String, String> diary;

    private Father(House house, String name) {
        super(house, name);
        personType = Fasada.allClasses.father;
        diary = new HashMap<>();
    }

    public static Father getInstance(House house, String name) {
        if (singleton == null) {
            singleton = new Father(house, name);
            return singleton;
        } else {
            System.out.println("This house already has its father. No new will be created.");
            return singleton;
        }
    }

    @Override
    protected void sleep() {
        super.sleep();
        diary.put(personType.toString()+" "+ name +" sleeps", "activity");
    }

    @Override
    public void tick() {
        super.tick();
        if (request.allRequests() > 0) {
            if (request.hasTo(Request.Typ.work) > 0) {
                if (house.getPeopleFasada()
                         .getByType(Fasada.allClasses.girl) != null) {
                    house.getPeopleFasada()
                         .getByType(Fasada.allClasses.girl)
                         .addWorkRequest(request.getWork());
                    diary.put("passed work to girl", "activity");
                } else if (house.getPeopleFasada()
                                .getByType(Fasada.allClasses.boy) != null) {
                    house.getPeopleFasada()
                         .getByType(Fasada.allClasses.boy)
                         .addWorkRequest(request.getWork());
                    diary.put("passed work to boy", "activity");
                } else {
                    workSolve();
                    return;
                }
            }
            if (request.hasTo(Request.Typ.repairable) > 0) {
                if (request.hasTo(Request.Typ.repairable) > 2) {
                    if (house.getPeopleFasada()
                             .getByType(Fasada.allClasses.boy) != null) {
                        house.getPeopleFasada()
                             .getByType(Fasada.allClasses.boy)
                             .addRepairableRequest(request.getRepairable());
                    }
                }
                request.getRepairable()
                       .repair(this);
                return;
            }
            if (request.hasTo(Request.Typ.animal) > 0) {
                request.getAnimal()
                       .feed();
            }
        }
        Random random = new Random();
        if (random.nextBoolean()) {
            Appliance a = house.getApplianceFasada()
                               .getByType(Fasada.allClasses.television);
            if (a != null) {
                a.use(this);
                return;
            }
            sport();
        }
    }

    @Override
    public void report(Reporter reporter) {
        for (String key : diary.keySet()) {
            if (!diary.get(key)
                      .equals("activity")) {
                reporter.eventCatch(key, diary.get(key));
            } else reporter.activityCatch(personType.toString() + " " + name, key);
        }
        diary = new HashMap<>();
    }

    @Override
    protected void wakeUp() {
        super.wakeUp();
        diary.put(personType.toString()+" "+ name +" wakes up", "activity");
    }
}