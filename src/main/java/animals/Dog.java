package animals;

import general.Fasada;
import general.House;
import general.Room;
import general.Tickable;
import people.Person;

public class Dog extends Animal{
    private Person master;
    private boolean validMaster;
    private Room allowed;
    public Dog(House house) {
        super(house);
        validMaster=false;
        animalType = Fasada.allClasses.dog;
    }

    @Override
    public void tick() {
        hunger++;
        if(hunger>12){
            if(master==null || hunger>24) {
                for (Person p : room.getPeople()) {
                    p.addAnimalRequest(this);
                }
                validMaster=false;
            }
            master.addAnimalRequest(this);
        }
        if(master!=null && master.getRoom()==house.getRoomFasada().getOutside()){
            house.getRoomFasada().getOutside().addPropriet(this, room);
        }
        else if (allowed!=null){
            allowed.addPropriet(this, room);
        }
    }

    @Override
    public void feed(Person person) {
        if(room!=person.getRoom())person.getRoom().addPropriet(this, room);
        super.feed(person);
        if(!validMaster)master=person;

    }

    @Override
    public void report() {

    }

    @Override
    public void play() {

    }

    public void place(Room room) {
        if (allowed==null)allowed=room;
        this.room=room;
    }
}