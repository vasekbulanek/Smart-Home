package general;

import appliance.ApplianceFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class RoomFasada extends Fasada{
    HashMap<String, Integer> lasts;
    LinkedList <Fasada> fasadas;
    LinkedList<Room> roomLinkedList;
    public RoomFasada(House house, String initFile ) {
        super(house, initFile);
        fasadas=new LinkedList<>();
        fasadas.add(house.getAnimalFasada());
        fasadas.add(house.getApplianceFasada());
        fasadas.add(house.getEquipmentFasada());
        fasadas.add(house.getPeopleFasada());
        lasts=new HashMap<>();
        roomLinkedList=new LinkedList<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(initFile));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject room = (JSONObject) jsonObject.get("room");
            for (Object key:room.keySet()) {
                JSONObject inside = (JSONObject) room.get(key);
                //System.out.println(key.toString());
                Room room1 = new Room(key.toString());
                for (Object thing:inside.keySet()) {
                    String name = thing.toString();
                    int count = Integer.parseInt(inside.get(thing).toString());
                    //System.out.println(name+" "+count);
                    for (; count > 0; count--) {
                        if (lasts.containsKey(name)) {
                            for (Fasada fasada : fasadas) {
                                if (fasada.getNextByType(name, lasts.get(name)) != null) {
                                    room1.addPropriet((Tickable) fasada.getNextByType(name, lasts.get(name)));
                                    lasts.replace(name, fasada.getNextByType(name, lasts.get(name)).hashCode());
                                }
                            }
                        } else {
                            for (Fasada fasada : fasadas) {
                                if (fasada.getByType(name) != null) {
                                    room1.addPropriet((Tickable) fasada.getByType(name));
                                    lasts.put(name, fasada.getByType(name).hashCode());
                                }
                            }
                        }
                    }
                }

                roomLinkedList.add(room1);
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        for (Room r :roomLinkedList) {
            r.printContent();
        }
    }

    @Override
    public Object getByType(String k) {
        return null;
    }

    @Override
    public Object getNextByType(String type, int hash) {
        return null;
    }

    private void nextKey(String k, int hash){
        if(lasts.containsKey(k))lasts.replace(k, hash);
        else lasts.put(k, hash);
    }


    @Override
    public void sendTicks() {

    }

}
