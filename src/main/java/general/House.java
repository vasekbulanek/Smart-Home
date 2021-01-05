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
    public Reporter reporter;

    public House() {
        reporter = new Reporter(this);
        time = new Time(reporter);
        weather = new Weather();
        String initFile = "src/main/resources/init.json";
        peopleFasada = new PeopleFasada(this, initFile);
        animalFasada = new AnimalFasada(this, initFile);
        applianceFasada = new ApplianceFasada(this, initFile);
        equipmentFasada = new EquipmentFasada(this, initFile);
        roomFasada = new RoomFasada(this, initFile);
        getPeopleFasada().checkRooms();
        getAnimalFasada().checkRooms();
        peopleFasada.sendTicks();
        reporter.houseConfigurationReport();
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
