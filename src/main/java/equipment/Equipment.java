package equipment;

import general.*;
import people.Person;

public abstract class Equipment implements Repairable {
    public boolean functionality;
    protected House house;
    protected Room room;
    protected Room whenTidy;
    Fasada.allClasses equipmentType;

    public Equipment(House house) {
        this.house = house;
        functionality = true;
    }

    public abstract void Tidy();

    private void breakDown() {
        functionality = false;
    }

    public abstract void report(Reporter reporter);

    public abstract void tick();

    public Room getRoom() {
        if (room != null) return room;
        house.getRoomFasada()
             .getOutside()
             .addPropriet(this, null);
        return house.getRoomFasada()
                    .getOutside();
    }

    @Override
    public void place(Room room) {
        if (whenTidy == null) {
            whenTidy = room;
        }
        this.room = room;
    }

    public abstract int getTimeToService();

    public abstract boolean isBroken();

    public abstract boolean isInUse();

    protected abstract void refreshService();

    public void repair(Person person) {
        if (!functionality || getTimeToService() < 24) {
            functionality = true;
            refreshService();
            person.delay();
        }
    }

    public Fasada.allClasses getEquipmentType() {
        return equipmentType;
    }
}
