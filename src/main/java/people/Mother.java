package people;
import general.House;
import general.Object;

public class Mother extends Person implements Object {
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
    protected void useAppliance() {

    }
}