package general;

public abstract class Fasada {
    House house; // We need everybody to be able to see Fasadas, house will have the getters
    String initFile;

    public Fasada(House house, String initFile) {
        this.house = house;
        this.initFile=initFile;
    }


    public abstract void sendTicks();
}
