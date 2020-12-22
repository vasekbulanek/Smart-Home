package people;
import general.House;
import general.Room;
import general.Tickable;

public class Boy extends Person implements Tickable {
    House house;
    public Boy(House house) {
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
