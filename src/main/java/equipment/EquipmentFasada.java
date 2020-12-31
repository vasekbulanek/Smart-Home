package equipment;

import general.Fasada;
import general.House;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

public class EquipmentFasada extends Fasada {
    private LinkedList<Equipment> equipment;

    public EquipmentFasada(House house, String initFile) {
        super(house, initFile);
        equipment = new LinkedList<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(initFile));
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject people = (JSONObject) jsonObject.get("equipment");
            for (Object key : people.keySet()) {
                String type = key.toString();
                int num = Integer.parseInt(people.get(key)
                        .toString());
                System.out.println("There will be " + num + " " + type);
                switch (type) {
                    case ("Sky"):
                        equipment.add(new Sky(house));
                        break;
                    case ("Bicycle"):
                        equipment.add(new Bicycle(house));
                        break;
                    default:
                        System.out.println("There is unknown equipment type " + type + ". Check init.json, please.");
                }
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendTicks() {
        for (Equipment e : equipment) {
            e.tick();
        }
    }

    public Equipment getByType(String type) { // I am not sure if it is the best way, but it works
        for (Equipment e : equipment) {
            if (e.getClass()
                    .toString()
                    .equals("class equipment." + type)) {
                return e;
            }
        }
        return null;
    }

    public Equipment getNextByType(String type, int hash) {
        boolean found = false;
        for (Equipment e : equipment) {
            if (!found && e.hashCode() == hash) found = true;
            else if (found && e.getClass()
                    .toString()
                    .equals("class equipment." + type)) {
                return e;
            }
        }
        return null;
    }

}
