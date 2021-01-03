package people;

import animals.Animal;
import appliance.Appliance;
import appliance.workItems.Work;
import equipment.Equipment;
import general.Repairable;

import java.util.LinkedList;

public class Request {
    private final LinkedList<Person> personLinkedList;
    private final LinkedList<Animal> animalLinkedList;
    private final LinkedList<Repairable> repairableLinkedList;
    private final LinkedList<Work> workLinkedList;

    public enum Typ {
        person, animal, repairable, work
    }

    public Request() {
        personLinkedList = new LinkedList<>();
        animalLinkedList = new LinkedList<>();
        repairableLinkedList = new LinkedList<>();
        workLinkedList = new LinkedList<>();
    }

    public void addPerson(Person person) {
        personLinkedList.add(person);
    }

    public void addAnimal(Animal animal) {
        animalLinkedList.add(animal);
    }

    public void addRepairable(Repairable repairable) {
        repairableLinkedList.add(repairable);
    }

    public void addWork(Work work) {
        workLinkedList.add(work);
    }


    int hasTo(Typ typ) {
        switch (typ) {
            case animal:
                return animalLinkedList.size();
            case person:
                return personLinkedList.size();
            case repairable:
                return repairableLinkedList.size();
            case work:
                return workLinkedList.size();
            default:
                return 0;
        }
    }

    protected int allRequests() {
        return repairableLinkedList.size() + animalLinkedList.size() + personLinkedList.size() + workLinkedList.size();
    }
    protected Typ getFirst(){
        if (!personLinkedList.isEmpty())return Typ.person;
        if (!animalLinkedList.isEmpty())return Typ.animal;
        if (!repairableLinkedList.isEmpty())return Typ.repairable;
        if (!workLinkedList.isEmpty())return Typ.work;
        return null;
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

    protected Repairable getRepairable() {
        Repairable repairable = repairableLinkedList.get(0);
        repairableLinkedList.remove(0);
        return repairable;
    }
    protected Work getWork() {
        Work work = workLinkedList.get(0);
        workLinkedList.remove(0);
        return work;
    }
}
