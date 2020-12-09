package appliance;

import general.Tickable;

public abstract class Appliance implements Tickable {
    protected int consective;
    protected int manual;
    protected boolean warranty;
    protected boolean functionality;

    public abstract void use();
    public void breakDown(){
        functionality=false;
    }
    public void repair(){
        functionality=true;
    }

    public abstract void report();
    public abstract void tick();

}
