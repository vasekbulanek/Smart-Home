package people;
import general.House;
import general.Object;

public class Baby extends Person implements Object{
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
    protected void useAppliance() {

    }
}
