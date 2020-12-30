package people;

import animals.Animal;
import appliance.Appliance;
import equipment.Equipment;

public abstract class Person {
    int hunger;
    int mood;
    int health;
    boolean sleeping;
    private final Request request = new Request();

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

    protected abstract void report();

    protected abstract void tick();

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


