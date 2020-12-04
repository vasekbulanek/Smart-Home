package animals;

import general.Fasada;
import general.House;

public class AnimalFasada extends Fasada{
    // datova struktura pro zvirata


    public AnimalFasada(House house) {
        super(house);
    }

    public void sendTicks(){

    }
    public Animal getDog(){
        return null;
    }
    public Animal getCat(){
        return null;
    }
    public Animal getBird(){
        return null;
    }
}
