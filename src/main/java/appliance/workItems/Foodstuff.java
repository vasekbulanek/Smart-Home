package appliance.workItems;

import appliance.Appliance;
import appliance.Stove;
import general.House;

public class Foodstuff implements Work{
    private stateFood currentState;
    House house;
    private int portions;

    private enum stateFood{
        raw, cut, prepared, trash
    }
    public Foodstuff(House house) {
        this.house=house;
        currentState= stateFood.raw;
        portions=0;
    }

    public void beEaten(){
        if(currentState==stateFood.prepared) {
            portions--;
            if(portions==0) {
                currentState = stateFood.trash;
                house.getPeopleFasada().getByType("Mother").addWorkRequest(this);
            }
        }
    }
    public boolean work(){
        if(currentState== stateFood.raw ){
            currentState = stateFood.cut;
            house.getPeopleFasada().getByType("Mother").addWorkRequest(this);
            return true;
        }
        if(currentState==stateFood.trash){
            currentState=stateFood.raw;
            house.getPeopleFasada().getByType("Mother").addWorkRequest(this);
            return true;
        }
        return false;
    }

    @Override
    public boolean work(Appliance appliance) {
        if(appliance.toString().contains("Stove.")){
            return work((Stove) appliance);
        }
        return false;
    }

    @Override
    public String need() {
        if(currentState==stateFood.cut)return "Stove";
        return null;
    }

    public boolean work(Stove stove){
        stove.use();
        if(currentState== stateFood.cut ){
            currentState = stateFood.prepared;
            portions = house.getPeopleFasada().getSize()*2;
            return true;
        }
        return false;
    }

    public stateFood getCurrentState() {
        return currentState;
    }
}
