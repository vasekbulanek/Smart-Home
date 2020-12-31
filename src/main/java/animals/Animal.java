package animals;

import general.House;
import general.Room;
import general.Tickable;

public abstract class Animal implements Tickable {
    int hunger;
    int mood;
    int health;
    boolean sleeping;
    Room room;
    House house;

    public Animal(House house) {
        this.house = house;
    }

    public abstract void play();

    protected void eat() {
        hunger = 0;
    }

    protected void sleep() {
        sleeping = true;
    }

    protected void wakeUp() {
        sleeping = false;
    }

    public abstract void tick();

    public abstract void report();

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
