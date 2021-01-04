package animals;

import general.Fasada;
import general.House;
import general.Room;
import general.Tickable;
import people.Person;

public abstract class Animal implements Tickable {
    int hunger;
    int mood;
    int health;
    boolean sleeping;
    Room room;
    House house;
    String name;
    protected Fasada.allClasses animalType;

    public Animal(House house, String name) {
        this.house = house;
        this.name = name;
        hunger=0;
    }
    public abstract void play();

    protected void feed(Person person) {
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
        house.getRoomFasada().getOutside().addPropriet(this, room);
        return house.getRoomFasada().getOutside();
    }

    @Override
    public void place(Room room) {
        this.room=room;
    }
    public void feed(){
        hunger=0;
    }

    public int getHunger() {
        return hunger;
    }

    public boolean isSleeping() {
        return sleeping;
    }

    public Fasada.allClasses getAnimalType() {
        return animalType;
    }
}
