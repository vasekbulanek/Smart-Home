package people;

import animals.Animal;
import appliance.Appliance;
import equipment.Equipment;

import java.util.LinkedList;

public class Request {
    private final LinkedList<Person> personLinkedList;
    private final LinkedList<Animal> animalLinkedList;
    private final LinkedList<Appliance> applianceLinkedList;
    private final LinkedList<Equipment> equipmentLinkedList;

    public enum Typ {
        person, animal, appliance, equipment
    }

    public Request() {
        personLinkedList = new LinkedList<>();
        animalLinkedList = new LinkedList<>();
        applianceLinkedList = new LinkedList<>();
        equipmentLinkedList = new LinkedList<>();
    }

    public void addPerson(Person person) {
        personLinkedList.add(person);
    }

    public void addAnimal(Animal animal) {
        animalLinkedList.add(animal);
    }

    public void addAppliance(Appliance appliance) {
        applianceLinkedList.add(appliance);
    }

    public void addEquipment(Equipment equipment) {
        equipmentLinkedList.add(equipment);
    }


    int hasTo(Typ typ) {
        switch (typ) {
            case animal:
                return animalLinkedList.size();
            case person:
                return personLinkedList.size();
            case appliance:
                return applianceLinkedList.size();
            case equipment:
                return equipmentLinkedList.size();
            default:
                return 0;
        }
    }

    protected int allRequests() {
        return applianceLinkedList.size() + animalLinkedList.size() + personLinkedList.size() + equipmentLinkedList.size();
    }

    protected Person getPerson() {
        Person person = personLinkedList.get(0);
        personLinkedList.remove(0);
        return person;
    }

    protected Animal getAnimal() {
        Animal animal = animalLinkedList.get(0);
        animalLinkedList.remove(0);
        return animal;
    }

    protected Appliance getAppliance() {
        Appliance appliance = applianceLinkedList.get(0);
        applianceLinkedList.remove(0);
        return appliance;
    }
}
