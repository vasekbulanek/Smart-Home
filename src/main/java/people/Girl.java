package people;

import general.*;
import general.Tickable;

public class Girl extends Person implements Tickable {
    House house;

    public Girl(House house) {
        this.house = house;
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
