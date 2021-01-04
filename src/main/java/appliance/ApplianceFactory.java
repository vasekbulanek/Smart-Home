package appliance;

import general.House;
import org.json.simple.JSONObject;

// I do not know, if it is really factory
// May be it should be just a one of ApplianceFasada functions
public class ApplianceFactory {
    ApplianceFasada applianceFasada;
    House house;

    public ApplianceFactory(ApplianceFasada applianceFasada, House house) {
        this.applianceFasada = applianceFasada;
        this.house = house;
    }

    protected void create(Object key, JSONObject object) {
        JSONObject appliance = (JSONObject) object.get("appliance");
        int count = applianceFasada.countJsonItems(key.toString(), object);
        int elecOn = Integer.parseInt(((JSONObject) appliance.get(key)).get("electricity on")
                                                                       .toString());
        int elecOff = Integer.parseInt(((JSONObject) appliance.get(key)).get("electricity off")
                                                                        .toString());
        int water = Integer.parseInt(((JSONObject) appliance.get(key)).get("water")
                                                                      .toString());

        System.out.println("There will be " + count + " " + key.toString());
        switch (key.toString()) {
            case ("Blinds"):
                for (int i = 0; i < count; i++) applianceFasada.addToList(new Blinds(house, elecOn, elecOff));
                break;
            case ("Boiler"):
                for (int i = 0; i < count; i++) applianceFasada.addToList(new Boiler(house, elecOn, elecOff, water));
                break;
            case ("Fridge"):
                for (int i = 0; i < count; i++) applianceFasada.addToList(new Fridge(house, elecOn, elecOff));
                break;
            case ("Iron"):
                for (int i = 0; i < count; i++) applianceFasada.addToList(new Iron(house, elecOn, elecOff, water));
                break;
            case ("Phone"):
                for (int i = 0; i < count; i++) applianceFasada.addToList(new Phone(house, elecOn, elecOff));
                break;
            case ("Stove"):
                for (int i = 0; i < count; i++) applianceFasada.addToList(new Stove(house, elecOn, elecOff));
                break;
            case ("Television"):
                for (int i = 0; i < count; i++) applianceFasada.addToList(new Television(house, elecOn, elecOff));
                break;
            default:
                System.out.println("There is unknown appliance type " + key.toString() + ". Check init.json, please.");
        }
    }
}