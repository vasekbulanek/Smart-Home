package people;

import general.Fasada;

import general.House;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class PeopleFasada extends Fasada{
    Map<String, Person>personMap;

    //Todo: If is this generating OK, repeat it in some forms in all Fasadas
    //Todo: Prepare init.json and delete the curious neighbor
    public PeopleFasada(House house) {
        super(house);
        personMap = new HashMap<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/init.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject people = (JSONObject) jsonObject.get("people");
            for (Object key:people.keySet()) {
                String name = key.toString();
                String type = people.get(key).toString();
                System.out.println("Hi, I am "+name+" and I am "+type);
                switch (type){
                    case ("father"):
                        personMap.put(name, new Father());
                        break;
                    case ("mother"):
                        personMap.put(name, new Mother());
                        break;
                    case ("boy"):
                        personMap.put(name, new Boy());
                        break;
                    case ("girl"):
                        personMap.put(name, new Girl());
                        break;
                    case ("baby"):
                        personMap.put(name, new Baby());
                        break;
                    default:
                        System.out.println("There is unknown person type "+type+". Check init.json, please.");
                }
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void sendTicks() {
        for (Person person:personMap.values()) {
            person.tick();
        }

    }
    public Person getByName(String name){
        for (String key:personMap.keySet()) {
            if(name.equals(key)){
                return personMap.get(key);
            }
        }
        return null;
    }
    //Todo: check if is this function working
    public Person getByType(String type){
        for (Person key:personMap.values()) {
            if(key.getClass().toString().equals(type)){
                return key;
            }
        }
        return null;
    }

}
