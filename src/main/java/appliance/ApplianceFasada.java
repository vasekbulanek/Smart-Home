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
    private int waterPrice;
    private int electricityPrice;
    public ApplianceFasada(House house) {
        super(house);
        appliances=new LinkedList<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/init.json"));
            JSONObject jsonObject = (JSONObject) obj;
            waterPrice=Integer.parseInt(((JSONObject)jsonObject.get("prices")).get("water").toString());
            System.out.println("water "+waterPrice);
            electricityPrice=Integer.parseInt(((JSONObject)jsonObject.get("prices")).get("electricity").toString());
            JSONObject appliance = (JSONObject) jsonObject.get("appliance");
            ApplianceFactory applianceFactory = new ApplianceFactory(this);
            for (Object key:appliance.keySet()) {
                int num = Integer.parseInt(((JSONObject)appliance.get(key)).get("count").toString());
                int electrOn = Integer.parseInt(((JSONObject)appliance.get(key)).get("electricity on").toString());
                int electrOff = Integer.parseInt(((JSONObject)appliance.get(key)).get("electricity off").toString());
                int water0 = Integer.parseInt(((JSONObject)appliance.get(key)).get("water").toString());
                String type = appliance.get(key).toString();
                System.out.println("There will be "+num+" "+type);
                applianceFactory.create(type, num, electrOn, electrOff, water0);
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
    protected void addToList(Appliance appliance){
        appliances.add(appliance);
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

    public int getWaterPrice() {
        return waterPrice;
    }

    public int getElectricityPrice() {
        return electricityPrice;
    }
}
