package equipment;

import general.House;
import general.Repairable;
import general.Room;
import general.Tickable;
import people.Person;

public abstract class Equipment implements Repairable {
    public boolean free;
    public boolean functionality;
    House house;
    Room room;
    Room whenTidy;

    public Equipment(House house) {
        this.house=house;
        free = true;
        functionality = true;
    }

    public boolean use(Person person) {
        if (free) {
            free = false;
            house.getRoomFasada().getOutside().addPropriet(this, room);
            house.getRoomFasada().getOutside().addPropriet(person, room);
            person.setUsing(this);
            return true;
        } else return false;

    }

    public void Tidy(){
        free=true;
        if(whenTidy!=null) {
            whenTidy.addPropriet(this, room);
        }
    }

    private void breakDown() {
        functionality = false;
    }

    public abstract void report();

    public abstract void tick();

    public Room getRoom(){
        if(room!=null)return room;
        house.getRoomFasada().getOutside().addPropriet(this, null);
        return house.getRoomFasada().getOutside();
    }

    @Override
    public void place(Room room) {
        if(whenTidy==null)whenTidy=room;
        this.room=room;
    }

    public abstract int getTimeToService();
    public abstract boolean isBroken();

    public abstract boolean isInUse();

    protected abstract void refreshService();

    public void repair(Person person){
        if(!functionality || getTimeToService()<24){
            functionality=true;
            refreshService();
            person.delay();
        }
    }

}
