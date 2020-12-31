package appliance;

import general.House;
import general.Room;
import general.Tickable;

public abstract class Appliance implements Tickable {
    protected int consective;
    protected int manual;
    protected boolean warranty;
    protected boolean functionality;
    Room room;
    House house;

    public Appliance(House house) {
        this.house = house;
    }

    public abstract void use();

    public void breakDown() {
        functionality = false;
    }

    public void repair() {
        functionality = true;
    }

    public abstract void report();

    @Override
    public void place(Room room) {
        this.room=room;
    }

    public abstract void tick();

    public Room getRoom(){
        if(room!=null)return room;
        house.getRoomFasada().getOutside().addPropriet(this);
        return house.getRoomFasada().getOutside();
    }
}

