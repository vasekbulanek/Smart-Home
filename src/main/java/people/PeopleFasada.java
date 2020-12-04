package people;

import general.Fasada;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;


public class PeopleFasada extends Fasada{
    Map<String, Person>personMap;

    public PeopleFasada() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/init.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject people = (JSONObject) jsonObject.get("people");
            for (Object key:people.keySet()) {
                String name = key.toString();
                String type = people.get(key).toString();
                System.out.println("Hi, I am "+name+" and I am "+type);
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void sendTicks() {

    }
}
