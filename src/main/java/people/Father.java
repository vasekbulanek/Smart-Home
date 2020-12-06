package people;
import general.House;
import general.Object;


//Todo: Make Father Singleton
public class Father extends Person implements Object{
    House house;
    public Father(House house){
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
