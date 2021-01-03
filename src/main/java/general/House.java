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
    private RoomFasada roomFasada;
    private Time time;
    private Weather weather;

    public House() {
        time=new Time();
        weather = new Weather();
        String initFile = "src/main/resources/init.json";
        animalFasada = new AnimalFasada(this, initFile);
        applianceFasada = new ApplianceFasada(this, initFile);
        peopleFasada = new PeopleFasada(this, initFile);
        equipmentFasada = new EquipmentFasada(this, initFile);
        roomFasada = new RoomFasada(this, initFile);
        getPeopleFasada().checkRooms();
        getAnimalFasada().checkRooms();
        peopleFasada.sendTicks();
    }

    void run() {
        time.tick();
        weather.tick();
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

    public RoomFasada getRoomFasada() {
        return roomFasada;
    }

    public Time getTime() {
        return time;
    }

    public Weather getWeather() {
        return weather;
    }
}
