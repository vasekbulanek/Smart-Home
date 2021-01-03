package people;

import general.House;
import general.Room;
import general.Tickable;

public class Boy extends Person  {

    public Boy(House house) {
        super(house);
    }


    @Override
    public void tick() {
        if(request.allRequests()>0){

        }
        else sport();
    }

    @Override
    public void report() {

    }


    @Override
    protected void useAppliance() {

    }
}

