package appliance;

import general.Room;
import general.Tickable;

public abstract class Appliance implements Tickable {
    protected int consective;
    protected int manual;
    protected boolean warranty;
    protected boolean functionality;

    public abstract void use();

    public void breakDown() {
        functionality = false;
    }

    public void repair() {
        functionality = true;
    }

    public abstract void report();

    @Override
    public void place(Room room) {

    }

    public abstract void tick();

}
