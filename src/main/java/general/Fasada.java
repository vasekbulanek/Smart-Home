package general;

import people.Person;

public abstract class Fasada<T> {
    House house; // We need everybody to be able to see Fasadas, house will have the getters
    String initFile;

    public Fasada(House house, String initFile) {
        this.house = house;
        this.initFile = initFile;
    }

    abstract public T getByType(String k);

    abstract public T getNextByType(String type, int hash);

    public abstract void sendTicks();
}
