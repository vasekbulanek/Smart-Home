package people;

import animals.Animal;
import appliance.Appliance;
import equipment.Equipment;
import general.House;
import general.Observer;
import general.Room;
import general.Tickable;
import appliance.workItems.Foodstuff;

public abstract class Person  extends Observer implements Tickable {
    int hunger;
    int mood;
    int health;
    boolean sleeping;
    protected final Request request = new Request();
    Room room;
    House house;

    public Person(House house) {
        super(house.getTime());
        this.house = house;
        hunger=0;
        sleeping=false;
    }


    protected abstract void useAppliance();

    protected void eat(Foodstuff foodstuff) {
        foodstuff.beEaten();
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
        if(house.getRoomFasada()==null)return null;
        house.getRoomFasada().getOutside().addPropriet(this, room);
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

    public void update(){
        if(!sleeping && !house.getTime().isDay()){
            if(house.getRoomFasada().getByName("bedroom")!=null){
                ((Room)house.getRoomFasada().getByName("bedroom")).addPropriet(this, room);
            }
            sleeping=true;
        }
        else if(sleeping && house.getTime().isDay()){
            sleeping=false;
        }
    }
}


