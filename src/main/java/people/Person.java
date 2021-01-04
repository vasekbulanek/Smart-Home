package people;

import animals.Animal;
import appliance.Fridge;
import appliance.workItems.Work;
import equipment.Equipment;
import general.*;
import appliance.workItems.Foodstuff;

public abstract class Person  extends Observer implements Tickable {
    int hunger;
    protected final Request request;
    Room room;
    House house;
    protected Equipment using;
    protected longActivity activity;
    protected general.Fasada.allClasses personType;

    protected enum longActivity{
        no, sleep, sport, other
    }

    public Person(House house) {
        super(house.getTime());
        this.house = house;
        hunger=0;
        activity=longActivity.no;
        request = new Request();
    }


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
        Fasada.allClasses eq = Fasada.allClasses.sky;
        if (house.getWeather().getTemperature()>0){
            eq = Fasada.allClasses.bicycle;
        }
        Equipment equipment = house.getEquipmentFasada().getByType(eq);
        if(equipment==null)return false;
        while (equipment.isInUse()){
            equipment = house.getEquipmentFasada().getNextByType(eq, equipment.hashCode());
            if (equipment==null)return false;
        }
        if (equipment.isBroken()){
            house.getPeopleFasada().getByType(Fasada.allClasses.father).addRepairableRequest(equipment);
            return false;
        }
        house.getRoomFasada().getOutside().addPropriet(this, room);
        if(equipment.use(this)){
            activity=longActivity.sport;
            return true;
        }
        return false;
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

    public void tick(){
        hunger++;
        if(hunger>8){
            Fridge fridge = (Fridge) house.getApplianceFasada().getByType(Fasada.allClasses.fridge);
            if(fridge!=null)fridge.getContent().beEaten();
        }
        if(activity!=longActivity.no){
            if(activity==longActivity.sleep){
                return;
            }
            if (activity == longActivity.sport && using!=null){
                using.Tidy();
                if(house.getApplianceFasada().getByType(Fasada.allClasses.boiler)!=null){
                    house.getApplianceFasada().getByType(Fasada.allClasses.boiler).use(this);
                }else house.getRoomFasada().getRoomLinkedList().get(0).addPropriet(this, room);
                using=null;
            }
            activity=longActivity.no;
            return;
        }
    }

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

    protected void workSolve(){
        Work r = request.getWork();
        Fasada.allClasses name = r.need();
        if(name!=null){
            r.work(house.getApplianceFasada().getByType(name), this);
        }
        else r.work();
    }

    public Fasada.allClasses getPersonType() {
        return personType;
    }
}


