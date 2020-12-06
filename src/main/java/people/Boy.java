package people;
import general.House;
import general.Object;

public class Boy extends Person implements Object{
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
