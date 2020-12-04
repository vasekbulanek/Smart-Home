package appliance;

public abstract class Appliance {
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

    protected abstract void report();
    protected abstract void tick();

}
