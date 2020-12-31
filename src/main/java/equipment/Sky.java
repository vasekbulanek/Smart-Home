package equipment;

import general.House;
import general.Room;
import general.Tickable;

import java.util.Random;

public class Sky extends Equipment  {
    private boolean broken;
    private int timeToService;
    private final int service = 50;
    private final int using = 4;
    private final int OKusageLikehood = 4;
    private boolean inUse;

    public Sky(House  house) {
        super(house);
        broken = false;
        timeToService = service;
        inUse = false;
    }

    public boolean use() {
        if (inUse) return false;
        if (broken) return false;
        if (timeToService <= 0) return false;
        timeToService -= using;
        inUse = true;
        return true;
    }

    /*
     * Returns true if putting back was successful, false if broken
     */
    public boolean back() {
        Random random = new Random();
        inUse = false;
        for (int i = 0; i < OKusageLikehood; i++) {
            if (random.nextBoolean()) return true;
        }
        broken = true;
        return false;
    }

    @Override
    public void tick() {

    }

    @Override
    public void report() {

    }


    public int getTimeToService() {
        return timeToService;
    }

    public boolean isBroken() {
        return broken;
    }

    public boolean isInUse() {
        return inUse;
    }
}