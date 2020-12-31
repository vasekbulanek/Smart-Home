package equipment;

import general.House;
import general.Room;
import general.Tickable;

import java.util.Random;

public class Bicycle extends Equipment implements Tickable {
    private boolean broken;
    private int timeToService;
    private final int service = 200;
    private final int using = 3;
    private final int OKusageLikehood = 5;
    private boolean inUse = false;

    public Bicycle(House house) {
        super(house);
        broken = false;
        timeToService = service;
    }

    public boolean use() {
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
        timeToService--;
    }

    @Override
    public void report() {

    }

    @Override
    public void place(Room room) {

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
