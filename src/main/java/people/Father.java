package people;

import general.House;
import general.Room;
import general.Tickable;


public class Father extends Person  {
    private static Father singleton = null;

    private Father(House house) {
        super(house);
    }

    public static Father getInstance(House house) {
        if (singleton == null) {
            singleton = new Father(house);
            return singleton;
        } else {
            System.out.println("This house already has its father. No new will be created.");
            return singleton;
        }
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