package animals;

import general.*;
import people.Person;

public abstract class Animal implements Tickable {
    int hunger;
    Room room;
    House house;
    String name;
    protected Fasada.allClasses animalType;

    public Animal(House house, String name) {
        this.house = house;
        this.name = name;
        hunger = 0;
    }

    public abstract void play(Person person);

    protected void feed(Person person) {
        hunger = 0;
    }

    public abstract void tick();

    public abstract void report(Reporter reporter);

    public Room getRoom() {
        if (room != null) return room;
        house.getRoomFasada()
             .getOutside()
             .addPropriet(this, room);
        return house.getRoomFasada()
                    .getOutside();
    }

    @Override
    public void place(Room room) {
        this.room = room;
    }

    public void feed() {
        hunger = 0;
    }

    public int getHunger() {
        return hunger;
    }

    public Fasada.allClasses getAnimalType() {
        return animalType;
    }
}
