package people;

import animals.Animal;
import appliance.Appliance;
import equipment.Equipment;
import general.House;
import general.Room;
import general.Tickable;

public abstract class Person implements Tickable {
    int hunger;
    int mood;
    int health;
    boolean sleeping;
    private final Request request = new Request();
    Room room;
    House house;

    public Person(House house) {
        this.house = house;
    }

    protected abstract void useAppliance();

    protected void eat() {
        hunger = 0;
    }

    protected void sleep() {
        sleeping = true;
    }

    protected void wakeUp() {
        sleeping = false;
    }

    protected void sport() {

    }
    public Room getRoom(){
        if(room!=null)return room;
        house.getRoomFasada().getOutside().addPropriet(this);
        return house.getRoomFasada().getOutside();
    }

    @Override
    public void place(Room room) {
        this.room=room;
    }

    public abstract void report();

    public abstract void tick();

    public void addPersonRequest(Person person) {
        request.addPerson(person);
    }

    public void addAnimalRequest(Animal animal) {
        request.addAnimal(animal);
    }

    public void addApplianceRequest(Appliance appliance) {
        request.addAppliance(appliance);
    }

    public void addEquipment(Equipment equipment) {
        request.addEquipment(equipment);
    }
}


