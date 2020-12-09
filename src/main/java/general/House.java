package general;

import animals.AnimalFasada;
import appliance.ApplianceFasada;
import equipment.EquipmentFasada;
import people.PeopleFasada;
import people.Person;

public class House {
    private AnimalFasada animalFasada;
    private ApplianceFasada applianceFasada;
    private PeopleFasada peopleFasada;
    private EquipmentFasada equipmentFasada;

    public House() {
        animalFasada = new AnimalFasada(this);
        applianceFasada = new ApplianceFasada(this);
        peopleFasada = new PeopleFasada(this);
        equipmentFasada = new EquipmentFasada(this);
        peopleFasada.sendTicks();
    }

    void run(){
        animalFasada.sendTicks();
        applianceFasada.sendTicks();
        peopleFasada.sendTicks();
        equipmentFasada.sendTicks();
    }

    public AnimalFasada getAnimalFasada() {
        return animalFasada;
    }

    public ApplianceFasada getApplianceFasada() {
        return applianceFasada;
    }

    public PeopleFasada getPeopleFasada() {
        return peopleFasada;
    }

    public EquipmentFasada getEquipmentFasada() {
        return equipmentFasada;
    }
}
