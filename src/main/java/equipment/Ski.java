package equipment;

import general.*;
import people.Person;

import java.util.Random;

public class Ski extends Equipment {
    private boolean broken;
    private int timeToService;
    private final int service = 50 * 24;
    private final int using = 4;
    private final int OKusageLikehood = 4;
    private boolean inUse;

    public Ski(House house) {
        super(house);
        broken = false;
        timeToService = service;
        inUse = false;
        equipmentType = Fasada.allClasses.ski;
    }

    public boolean use(Person person) {
        if (!inUse) {
            if (broken || timeToService <= 0) {
                house.getPeopleFasada()
                     .getByType(Fasada.allClasses.father)
                     .addRepairableRequest(this);
                return false;
            }
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
        }
        Random random = new Random();
        inUse = false;
        for (int i = 0; i < OKusageLikehood; i++) {
            if (random.nextBoolean()) return;
        }
        broken = true;
        house.getPeopleFasada()
             .getByType(Fasada.allClasses.father)
             .addRepairableRequest(this);
    }

    @Override
    public void tick() {
        timeToService--;
    }

    @Override
    public void report(Reporter reporter) {

    }

    public int getTimeToService() {
        return timeToService;
    }

    protected void refreshService() {
        timeToService = service;
    }

    public boolean isBroken() {
        return broken;
    }

    public boolean isInUse() {
        return inUse;
    }
}