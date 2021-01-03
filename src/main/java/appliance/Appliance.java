package appliance;

import general.House;
import general.Repairable;
import general.Room;
import general.Tickable;
import people.Person;

public abstract class Appliance implements Repairable {
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
    public abstract void report();

    @Override
    public void place(Room room) {
        this.room=room;
    }

    public abstract void tick();

    public Room getRoom(){
        if(room!=null)return room;
        house.getRoomFasada().getOutside().addPropriet(this, room);
        return house.getRoomFasada().getOutside();
    }
    public void repair(Person person){
        if(!functionality){
            functionality=true;
            person.delay();
        }
    }
}

