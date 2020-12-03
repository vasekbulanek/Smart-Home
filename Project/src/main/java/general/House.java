package general;

import animals.AnimalFasada;
import appliance.ApplianceFasada;
import equipment.EquipmentFasada;
import people.PeopleFasada;

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

    void run(){
        animalFasada.sendTicks();
        applianceFasada.sendTicks();
        peopleFasada.sendTicks();
        equipmentFasada.sendTicks();
    }
}
