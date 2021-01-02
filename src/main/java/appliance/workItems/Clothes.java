package appliance.workItems;

import appliance.Iron;
import appliance.WashingMachine;

public class Clothes {
    private stateCloth currentState;
    private enum stateCloth{
        wearied, dirty, washed, ironed
    }
    public boolean work(Iron iron){
        iron.use();
        if(currentState==stateCloth.washed){
            currentState=stateCloth.ironed;
            return true;
        }
        return false;
    }
   public void work(WashingMachine washingMachine){
        washingMachine.use();
        if(currentState==stateCloth.dirty){
            currentState=stateCloth.washed;
        }
    }
}
