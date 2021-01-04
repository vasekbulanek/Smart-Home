package equipment;

import general.Fasada;
import general.House;
import general.Room;
import general.Tickable;
import people.Person;

import java.util.Random;

public class Bicycle extends Equipment implements Tickable {
    private boolean broken;
    private int timeToService;
    private final int service = 24*60;
    private final int using = 3;
    private final int OKusageLikehood = 5;
    private boolean inUse;

    public Bicycle(House house) {
        super(house);
        broken = false;
        timeToService = service;
        equipmentType = Fasada.allClasses.bicycle;
        inUse = false;
    }

    public boolean use(Person person) {
        if (!inUse) {
            if ( broken || timeToService <= 0){
                house.getPeopleFasada().getByType(Fasada.allClasses.father).addRepairableRequest(this);
                return false;
            }
            timeToService -= using;
            inUse = true;
            house.getRoomFasada().getOutside().addPropriet(this, room);
            house.getRoomFasada().getOutside().addPropriet(person, room);
            person.setUsing(this);
            return true;
        } else return false;

    }

    public void Tidy() {
        if(whenTidy!=null) {
            whenTidy.addPropriet(this, room);
        }
        Random random = new Random();
        inUse = false;
        for (int i = 0; i < OKusageLikehood; i++) {
            if (random.nextBoolean()) return;
        }
        broken = true;
        house.getPeopleFasada().getByType(Fasada.allClasses.father).addRepairableRequest(this);
    }

    @Override
    public void tick() {
        timeToService--;
    }

    @Override
    public void report() {

    }

    public int getTimeToService() {
        return timeToService;
    }

    protected void refreshService(){
        timeToService=service;
    }

    public boolean isBroken() {
        return broken && timeToService<0;
    }

    public boolean isInUse() {
        return inUse;
    }
}
