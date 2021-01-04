package appliance;

import general.Fasada;
import general.House;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

public class ApplianceFasada extends Fasada {
    private LinkedList<Appliance> appliances;
    private int waterPrice;
    private int electricityPrice;

    public ApplianceFasada(House house, String initFile) {
        super(house, initFile);
        appliances = new LinkedList<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(initFile));
            JSONObject jsonObject = (JSONObject) obj;
            waterPrice = Integer.parseInt(((JSONObject) jsonObject.get("prices")).get("water")
                    .toString());
            System.out.println("water " + waterPrice);
            electricityPrice = Integer.parseInt(((JSONObject) jsonObject.get("prices")).get("electricity")
                    .toString());
            JSONObject appliance = (JSONObject) jsonObject.get("appliance");
            ApplianceFactory applianceFactory = new ApplianceFactory(this, house);
            for (Object key : appliance.keySet()) {
                applianceFactory.create(key, jsonObject);
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void addToList(Appliance appliance) {
        appliances.add(appliance);
    }

    @Override
    public void sendTicks() {
        for (Appliance a : appliances) {
            a.tick();
        }
    }

    public Appliance getByType(allClasses type) { // I am not sure if it is the best way, but it works
        for (Appliance a : appliances) {
            if (type== a.getApplianceType()) {
                return a;
            }
        }
        return null;
    }

    public Appliance getNextByType(allClasses type, int hash) {
        boolean found = false;
        for (Appliance a : appliances) {
            if (!found && a.hashCode() == hash) found = true;
            else if (type == a.getApplianceType() && found) {
                return a;
            }
        }
        return null;
    }

    public LinkedList<Appliance> getAppliances() {
        return appliances;
    }

    public int getWaterPrice() {
        return waterPrice;
    }

    public int getElectricityPrice() {
        return electricityPrice;
    }
}
