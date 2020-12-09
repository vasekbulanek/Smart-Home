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
    protected void useAppliance() {

    }
}
