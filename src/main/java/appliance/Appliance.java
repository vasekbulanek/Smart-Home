package appliance;

import general.House;
import general.Repairable;
import general.Room;
import general.Tickable;
import people.Person;

import java.util.Random;

public abstract class Appliance implements Repairable {
    protected int consective;
    protected int manual;
    protected boolean warranty;
    protected boolean functionality;
    protected boolean on;
    protected int usedElectricity;
    protected int usedWater;
    Room room;
    House house;

    public Appliance(House house) {
        this.house = house;
    }

    public boolean use(Person person){
        room.addPropriet(person, person.getRoom());
        Random random = new Random();
        if(random.nextInt(100)==1)breakDown();
        on=true;
        return functionality;
    }

    protected void breakDown() {
        house.getPeopleFasada().getByType("Father").addRepairableRequest(this);
        functionality = false;
    }

    public abstract void report();

    @Override
    public void place(Room room) {
        this.room = room;
    }

    public abstract void tick();

    public Room getRoom() {
        if (room != null) return room;
        house.getRoomFasada()
             .getOutside()
             .addPropriet(this, room);
        return house.getRoomFasada()
                    .getOutside();
    }

    public void repair(Person person) {
        if (!functionality) {
            functionality = true;
            person.delay();
        }
    }

    public int getUsedElectricity() {
        return usedElectricity;
    }

    public int getUsedWater() {
        return usedWater;
    }

    public void annulConsuption() {
        this.usedElectricity = 0;
        this.usedWater = 0;
    }
}

