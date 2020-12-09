package people;
import general.House;
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
    protected void useAppliance() {

    }
}
