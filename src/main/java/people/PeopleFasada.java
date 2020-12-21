package people;

import general.Fasada;
import general.House;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class PeopleFasada extends Fasada{
    Map<String, Person>personMap;

    public PeopleFasada(House house, String initFile) {
        super(house, initFile);
        personMap = new HashMap<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(initFile));
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject people = (JSONObject) jsonObject.get("people");
            for (Object key:people.keySet()) {
                String name = key.toString();
                String type = people.get(key).toString();
                System.out.println("Hi, I am "+name+" and I am "+type);
                switch (type){
                    case ("Father"):
                        Father father = Father.getInstance(house);
                        if(!personMap.containsValue(father)) personMap.put(name, father);
                        break;
                    case ("Mother"):
                        Mother mother = Mother.getInstance(house);
                        if(!personMap.containsValue(mother)) personMap.put(name, mother);
                        break;
                    case ("Boy"):
                        personMap.put(name, new Boy(house));
                        break;
                    case ("Girl"):
                        personMap.put(name, new Girl(house));
                        break;
                    case ("Baby"):
                        personMap.put(name, new Baby(house));
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

    public Person getByType(String type){ // I am not sure if it is the best way, but it works
        for (Person key:personMap.values()) {
            if(key.getClass().toString().equals("class people."+type)){
                return key;
            }
        }
        return null;
    }
    public Person getNextByType(String type, int hash){
        boolean found=false;
        for (Person key:personMap.values()) {
            if(!found && key.hashCode()==hash)found=true;
            else if(found && key.getClass().toString().equals("class people."+type)){
                return key;
            }
        }
        return null;
    }

}
