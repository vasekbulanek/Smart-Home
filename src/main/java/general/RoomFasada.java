package general;

import appliance.ApplianceFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class RoomFasada extends Fasada{
    HashMap<String, Integer> lasts;
    public RoomFasada(House house, String initFile ) {
        super(house, initFile);
        lasts=new HashMap<>();
    }
    private void nextKey(String k, int hash){
        if(lasts.containsKey(k))lasts.replace(k, hash);
        else lasts.put(k, hash);

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(initFile));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject room = (JSONObject) jsonObject.get("room");
            for (Object key:room.keySet()) {


            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void sendTicks() {

    }

}
