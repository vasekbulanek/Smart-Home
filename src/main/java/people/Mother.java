package people;
import general.House;
import general.Object;

//Todo: Make Mother Singleton
public class Mother extends Person implements Object {
    House house;
    public Mother(House house){
        this.house=house;
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