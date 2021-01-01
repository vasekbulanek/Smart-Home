package general;

public class Time {
    private int clock;

    public Time() {
        clock = 8;
    }

    protected void tick(){
        clock++;
        clock=clock%24;
    }

    public int getClock() {
        return clock;
    }
    public boolean isDay(){
        return (clock>7 && clock<11);
    }

}
