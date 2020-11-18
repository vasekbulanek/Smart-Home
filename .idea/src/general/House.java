package general;
import appliance.*;
import people.*;
import animals.*;
import equipment.*;

public class House {
    private AnimalFasada animalFasada;
    private ApplianceFasada applianceFasada;
    private PeopleFasada peopleFasada;
    private EquipmentFasada equipmentFasada;

    public House() {
        animalFasada = new AnimalFasada();
        applianceFasada = new ApplianceFasada();
        peopleFasada = new PeopleFasada();
        equipmentFasada = new EquipmentFasada();
    }

    private void run(){
        animalFasada.sendTicks();
        applianceFasada.sendTicks();
        peopleFasada.sendTicks();
        equipmentFasada.sendTicks();
    }
}
