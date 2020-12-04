package general;

public abstract class Fasada {
    House house; // We need everybody to be able to see Fasadas, house will have the getters

    public Fasada(House house) {
        this.house = house;
    }


    public abstract void sendTicks();
}
