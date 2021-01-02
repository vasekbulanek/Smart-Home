package general;

public class Time extends Observable{
    private int clock;
    private int date;

    public Time() {
        clock = 8;
        date=1;
    }

    protected void tick(){
        clock++;
        clock=clock%24;
        if (clock==0){
            date++;
        }
        if (clock==7 || clock==22)change();
    }

    public int getClock() {
        return clock;
    }
    public boolean isDay(){
        return (clock>6 && clock<22);
    }

    public int getDate() {
        return date;
    }
}
