package animals;

import general.Fasada;
import general.House;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import people.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class AnimalFasada extends Fasada{
    private HashMap<String, Animal> animalMap;


    public AnimalFasada(House house, String initFile) {
        super(house, initFile);
        animalMap=new HashMap<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(initFile));
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject people = (JSONObject) jsonObject.get("animals");
            for (Object key:people.keySet()) {
                String name = key.toString();
                String type = people.get(key).toString();
                System.out.println("Hi, I am "+name+" and I am "+type);
                switch (type){
                    case ("Bird"):
                        animalMap.put(name, new Bird());
                        break;
                    case ("Cat"):
                        animalMap.put(name, new Cat());
                        break;
                    case ("Dog"):
                        animalMap.put(name, new Dog());
                        break;
                    default:
                        System.out.println("There is unknown animal type "+type+". Check init.json, please.");
                }
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendTicks() {
        for (Animal animal:animalMap.values()) {
            animal.tick();
        }

    }
    public Animal getByName(String name){
        for (String key:animalMap.keySet()) {
            if(name.equals(key)){
                return animalMap.get(key);
            }
        }
        return null;
    }

    public Animal getByType(String type){ // I am not sure if it is the best way, but it works
        for (Animal key:animalMap.values()) {
            if(key.getClass().toString().equals("class animal."+type)){
                return key;
            }
        }
        return null;
    }

}
