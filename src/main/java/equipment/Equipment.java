package equipment;

import general.House;
import general.Room;
import general.Tickable;

public abstract class Equipment implements Tickable {
    public boolean free;
    public boolean functionality;
    House house;
    Room room;

    public Equipment(House house) {
        this.house=house;
        free = true;
        functionality = true;
    }

    public boolean use() {
        if (free) {
            free = false;
            return true;
        } else return false;
    }

    private void breakDown() {
        functionality = false;
    }

    public void repair() {
        functionality = true;
    }

    public abstract void report();

    public abstract void tick();

    public Room getRoom(){
        if(room!=null)return room;
        house.getRoomFasada().getOutside().addPropriet(this);
        return house.getRoomFasada().getOutside();
    }

    @Override
    public void place(Room room) {
        this.room=room;
    }

}
