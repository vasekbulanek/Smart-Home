package appliance.workItems;

import appliance.Appliance;
import appliance.Stove;

public class Foodstuff implements Work{
    private stateFood currentState;

    private enum stateFood{
        raw, cut, prepared, trash
    }
    public Foodstuff() {
        currentState= stateFood.raw;
    }

    public void beEaten(){
        currentState= stateFood.trash;
    }
    public boolean work(){
        if(currentState== stateFood.raw ){
            currentState = stateFood.cut;
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
            return true;
        }
        return false;
    }
    public boolean takeOutTrash_buyFood(){
        if(currentState== stateFood.trash){
            currentState= stateFood.raw;
            return true;
        }
        return false;
    }

    public stateFood getCurrentState() {
        return currentState;
    }
}
