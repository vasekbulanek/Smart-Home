package equipment;

import general.Fasada;
import general.House;
import general.Room;
import general.Tickable;
import people.Person;

import java.util.Random;

public class Sky extends Equipment  {
    private boolean broken;
    private int timeToService;
    private final int service = 50*24;
    private final int using = 4;
    private final int OKusageLikehood = 4;
    private boolean inUse;

    public Sky(House  house) {
        super(house);
        broken = false;
        timeToService = service;
        inUse = false;
        equipmentType = Fasada.allClasses.sky;
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
        return broken;
    }

    public boolean isInUse() {
        return inUse;
    }
}