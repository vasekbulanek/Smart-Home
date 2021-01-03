package appliance.workItems;

import appliance.Appliance;
import appliance.Iron;
import appliance.WashingMachine;

public class Clothes implements Work{
    private stateCloth currentState;

    private enum stateCloth{
        dirty, washed, ironed
    }

    public Clothes() {
        currentState = stateCloth.washed;
    }

    @Override
    public boolean work() {
        return false;
    }

    @Override
    public boolean work(Appliance appliance) {
        if(appliance.toString().contains("WashingMachine.")){
            return work((WashingMachine) appliance);
        }
        if(appliance.toString().contains("Iron.")){
            return work((Iron) appliance);
        }
        return false;
    }

    @Override
    public String need() {
        if (currentState==stateCloth.dirty)return "WashingMachine";
        if (currentState==stateCloth.washed)return "Iron";
        return null;
    }

    public boolean work(Iron iron){
        iron.use();
        if(currentState==stateCloth.washed){
            currentState=stateCloth.ironed;
            return true;
        }
        return false;
    }
   public boolean work(WashingMachine washingMachine){
        washingMachine.use();
        if(currentState==stateCloth.dirty){
            currentState=stateCloth.washed;
            return true;
        }
        return false;
    }
}
