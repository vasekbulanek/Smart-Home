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

public class ApplianceFasada extends Fasada{
    private LinkedList<Appliance> appliances;
    public ApplianceFasada(House house) {
        super(house);
        appliances=new LinkedList<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/init.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject people = (JSONObject) jsonObject.get("appliance");
            for (Object key:people.keySet()) {
                String type = key.toString();
                int num = Integer.parseInt(people.get(key).toString());
                System.out.println("There will be "+num+" "+type);
                switch (type){
                    case ("Blinds"):
                        appliances.add(new Blinds());
                        break;
                    case ("Boiler"):
                        appliances.add(new Boiler());
                        break;
                    case ("Fridge"):
                        appliances.add(new Fridge());
                        break;
                    case ("Iron"):
                        appliances.add(new Iron());
                        break;
                    case ("Phone"):
                        appliances.add(new Phone());
                        break;
                    case ("Stove"):
                        appliances.add(new Stove());
                        break;
                    case ("Television"):
                        appliances.add(new Television());
                        break;
                    default:
                        System.out.println("There is unknown appliance type "+type+". Check init.json, please.");
                }
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendTicks() {
        for (Appliance a : appliances) {
            a.tick();
        }
    }
    public Appliance getByType(String type){ // I am not sure if it is the best way, but it works
        Collections.shuffle(appliances);
        for (Appliance a:appliances) {
            if(a.getClass().toString().equals("class appliance."+type)){
                return a;
            }
        }
        return null;
    }

}
