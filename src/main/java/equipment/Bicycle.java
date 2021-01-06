package equipment;

import general.*;
import people.Person;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Bicycle extends Equipment implements Tickable {
    private boolean broken;
    private int timeToService;
    private final int service = 24 * 60;
    private final int using = 3;
    private final int OKusageLikehood = 5;
    private boolean inUse;
    private HashMap<String, String> eventLog;

    public Bicycle(House house) {
        super(house);
        broken = false;
        timeToService = service;
        equipmentType = Fasada.allClasses.bicycle;
        inUse = false;
        eventLog = new HashMap<>();
    }

    public boolean use(Person person) {
        if (!inUse) {
            if (broken || timeToService <= 0) {
                return false;
            }
            eventLog.put(person.getPersonType().toString()+" "+person.getName()+" is riding bicycle", "activity");
            timeToService -= using;
            inUse = true;
            house.getRoomFasada()
                 .getOutside()
                 .addPropriet(this, room);
            house.getRoomFasada()
                 .getOutside()
                 .addPropriet(person, room);
            person.setUsing(this);
            return true;
        } else return false;

    }

    public void Tidy() {
        if (whenTidy != null) {
            whenTidy.addPropriet(this, room);
            eventLog.put("bicycle is placed in "+room.getName(), "activity");
        }
        Random random = new Random();
        inUse = false;
        if(timeToService>0) {
            for (int i = 0; i < OKusageLikehood; i++) {
                if (random.nextBoolean()) return;
            }
        }
        broken = true;
        house.getPeopleFasada()
             .getByType(Fasada.allClasses.father)
             .addRepairableRequest(this);
        eventLog.put("broken bicycle", null);
    }

    @Override
    public void tick() {
        timeToService--;
    }

    @Override
    public void report(Reporter reporter) {
        for (String key : eventLog.keySet()) {
            if (eventLog.get(key)==null || !eventLog.get(key)
                    .equals("activity")) {
                reporter.eventCatch(key, eventLog.get(key));
            } else {reporter.activityCatch(equipmentType.toString(), key);}
        }
        eventLog = new HashMap<>();
    }

    @Override
    public void repair(Person person) {
            functionality = true;
            broken = false;
            refreshService();
            person.delay();
            eventLog.put("broken bicycle", person.getName());
    }

    public int getTimeToService() {
        return timeToService;
    }

    protected void refreshService() {
        timeToService = service;
    }

    public boolean isBroken() {
        return broken && timeToService < 0;
    }

    public boolean isInUse() {
        return inUse;
    }
}
