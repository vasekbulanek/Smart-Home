package people;
import general.House;
import general.Room;
import general.Tickable;

public class Mother extends Person implements Tickable {
    House house;
    private static Mother singleton = null;
    private Mother(House house){
        this.house=house;
    }
    public static Mother getInstance(House house){
        if(singleton==null){
            singleton= new Mother(house);
            return singleton;
        }
        else {
            System.out.println("This house already has its mother. No new will be created.");
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
    public void place(Room room) {

    }

    @Override
    protected void useAppliance() {

    }
}