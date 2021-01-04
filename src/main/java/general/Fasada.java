package general;

import org.json.simple.JSONObject;
import people.Person;

import java.util.Locale;

public abstract class Fasada<T> {
    House house; // We need everybody to be able to see Fasadas, house will have the getters
    String initFile;

    public enum allClasses{
        baby, boy, father, mother, girl, bicycle, ski, blinds,
        boiler, fridge, iron, phone, stove, television, washingMachine,
        cat, bird, dog, room
    }

    public Fasada(House house, String initFile) {
        this.house = house;
        this.initFile = initFile;
    }

    abstract public T getByType(allClasses allClasses);

    abstract public T getNextByType(allClasses allClasses, int hash);

    public abstract void sendTicks();

    public int countJsonItems(String name, JSONObject jsonObject){
        int ret=0;
        JSONObject rooms = (JSONObject) jsonObject.get("room");
        for (Object object:rooms.values()) {
            JSONObject room = (JSONObject) object;
            for (Object o:room.keySet()) {
                //System.out.println(o.toString()+" "+room.get(o).toString());
                if(((String) o).equals(name))ret+=Integer.parseInt(room.get(o).toString());
            }
        }
        return ret;
    }

}
