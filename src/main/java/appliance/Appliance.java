package appliance;

import general.*;
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
    protected Fasada.allClasses applianceType;
    Room room;
    House house;

    public Appliance(House house) {
        this.house = house;
    }

    public boolean use(Person person) {
        room.addPropriet(person, person.getRoom());
        Random random = new Random();
        if (random.nextInt(100) == 1) breakDown();
        on = true;
        return functionality;
    }

    protected abstract void breakDown();

    public abstract void report(Reporter reporter);

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

    public abstract void repair(Person person);

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

    public Fasada.allClasses getApplianceType() {
        return applianceType;
    }
}

