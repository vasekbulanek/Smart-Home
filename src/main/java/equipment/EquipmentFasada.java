package equipment;

import general.Fasada;
import general.House;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
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
                int num = countJsonItems(type, jsonObject);
                System.out.println("There will be " + num + " " + type);
                for (; num > 0; num--) {
                    switch (type) {
                        case ("Ski"):
                            equipment.add(new Ski(house));
                            break;
                        case ("Bicycle"):
                            equipment.add(new Bicycle(house));
                            break;
                        default:
                            System.out.println("There is unknown equipment type " + type + ". Check init.json, please.");
                    }
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

    public Equipment getByType(allClasses type) { // I am not sure if it is the best way, but it works
        for (Equipment e : equipment) {
            if (e.getEquipmentType() == type) {
                return e;
            }
        }
        return null;
    }

    public Equipment getNextByType(allClasses type, int hash) {
        boolean found = false;
        for (Equipment e : equipment) {
            if (!found && e.hashCode() == hash) found = true;
            else if (e.getEquipmentType() == type && found) {
                return e;
            }
        }
        return null;
    }

}
