package people;

import general.House;
import general.Room;
import general.Tickable;

public class Baby extends Person implements Tickable {
    public Baby(House house) {
        this.house = house;

    }

    House house;

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
