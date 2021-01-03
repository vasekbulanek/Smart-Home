package people;

import animals.Animal;
import appliance.Appliance;
import appliance.workItems.Work;
import equipment.Equipment;
import general.*;
import appliance.workItems.Foodstuff;

public abstract class Person  extends Observer implements Tickable {
    int hunger;
    int mood;
    int health;
    protected final Request request = new Request();
    Room room;
    House house;
    protected Equipment using;
    protected longActivity activity;

    protected enum longActivity{
        no, sleep, sport, other
    }

    public Person(House house) {
        super(house.getTime());
        this.house = house;
        hunger=0;
        activity=longActivity.no;
    }


    protected abstract void useAppliance();

    protected void eat(Foodstuff foodstuff) {
        foodstuff.beEaten();
        hunger = 0;
    }

    protected void sleep() {
        activity = longActivity.sleep;
    }

    protected void wakeUp() {
        activity = longActivity.no;
    }

    protected boolean sport() {
        String eq ="Sky";
        if (house.getWeather().getTemperature()>0){
            eq = "Bibycle";
        }
        Equipment equipment = house.getEquipmentFasada().getByType(eq);
        while (equipment.isInUse()){
            equipment = house.getEquipmentFasada().getNextByType(eq, equipment.hashCode());
            if (equipment==null)return false;
        }
        if (equipment.isBroken()){
            house.getPeopleFasada().getByType("Father").addRepairableRequest(equipment);
            return false;
        }
        equipment.use(this);
        activity=longActivity.sport;
        return true;

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

    public void addRepairableRequest(Repairable repairable) {
        request.addRepairable(repairable);
    }

    public void addWorkRequest(Work work) {
        request.addWork(work);
    }

    public void update(){
        if(activity!=longActivity.sleep && !house.getTime().isDay()){
            if(house.getRoomFasada().getByName("bedroom")!=null){
                ((Room)house.getRoomFasada().getByName("bedroom")).addPropriet(this, room);
            }
            activity=longActivity.sleep;
        }
        else if(activity==longActivity.sleep && house.getTime().isDay()){
            activity=longActivity.no;
        }
    }
    public void delay(){
        activity=longActivity.other;
    }

    public void setUsing(Equipment using) {
        this.using = using;
    }
}


