package people;

import general.House;
import general.Room;
import general.Tickable;

public class Boy extends Person implements Tickable {

    public Boy(House house) {
        super(house);
    }

    @Override
    public void tick() {

    }

    @Override
    public void report() {

    }

    @Override
    public void place(Room room) {

    }

    @Override
    protected void useAppliance() {

    }
}

