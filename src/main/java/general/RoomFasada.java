package general;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;

public class RoomFasada extends Fasada {
    HashMap<String, Integer> lasts;
    LinkedList<Fasada> fasadas;
    LinkedList<Room> roomLinkedList;
    Room outside;

    public RoomFasada(House house, String initFile) {
        super(house, initFile);
        fasadas = new LinkedList<>();
        fasadas.add(house.getAnimalFasada());
        fasadas.add(house.getApplianceFasada());
        fasadas.add(house.getEquipmentFasada());
        fasadas.add(house.getPeopleFasada());
        lasts = new HashMap<>();
        roomLinkedList = new LinkedList<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(initFile));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject room = (JSONObject) jsonObject.get("room");
            for (Object key : room.keySet()) {
                JSONObject inside = (JSONObject) room.get(key);
                //System.out.println(key.toString());
                Room room1 = new Room(key.toString(), house);
                for (Object thing : inside.keySet()) {
                    String name = thing.toString();
                    int count = Integer.parseInt(inside.get(thing)
                            .toString());
                    for (; count > 0; count--) {
                        if (convert(name) != null) {
                            if (lasts.containsKey(name)) {
                                for (Fasada fasada : fasadas) {
                                    if (fasada.getNextByType(convert(name), lasts.get(name)) != null) {
                                        room1.addPropriet((Tickable) fasada.getNextByType(convert(name), lasts.get(name)), null);
                                        lasts.replace(name, fasada.getNextByType(convert(name), lasts.get(name))
                                                .hashCode());
                                    }
                                }
                            } else {
                                for (Fasada fasada : fasadas) {
                                    if (fasada.getByType(convert(name)) != null) {
                                        room1.addPropriet((Tickable) fasada.getByType(convert(name)), null);
                                        lasts.put(name, fasada.getByType(convert(name))
                                                .hashCode());
                                    }
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
        outside = new Room("outside", house);
        roomLinkedList.add(outside);
    }

    @Override
    public Room getByType(allClasses allClasses) {
        return roomLinkedList.get(0);
    }

    @Override
    public Room getNextByType(allClasses type, int hash) {
        for (Room room :roomLinkedList) {
            if(room.hashCode()==hash)return room;
        }
        return null;
    }

    private void nextKey(String k, int hash) {
        if (lasts.containsKey(k)) lasts.replace(k, hash);
        else lasts.put(k, hash);
    }
    public Object getByName(String k) {
        for (Room room :roomLinkedList) {
            if(room.getName().equals(k))return room;
        }
        return null;
    }

    @Override
    public void sendTicks() {

    }

    public LinkedList<Room> getRoomLinkedList() {
        return roomLinkedList;
    }
    public Room getOutside(){
        return outside;
    }

    public static allClasses convert(String string) {
        for (allClasses a:allClasses.values()) {
            if(a.toString().contains(string.toLowerCase(Locale.ROOT)))return a;
        }
        return null;
    }
}

