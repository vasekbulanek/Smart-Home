package general;
import java.util.HashMap;

public class Reporter {
    House house;
    HashMap<String, String> events = new HashMap<>();   // ("#event", "#who managed to get rid of the event")

    public Reporter(House house) {
        this.house = house;
    }

    public void houseConfigurationReport() {
        System.out.println("--House Configuration Report--\nHouse:");
        for (Room room : house.getRoomFasada()
                              .getRoomLinkedList()) {
            System.out.println("\t" + room.getName() + ":");
            for (Tickable item : room.getPropriets()) {
                System.out.println("\t\t" + item.getClass()
                                                .getSimpleName());
            }
        }
    }

    public void newEvent(String event){
        
    }

    public void eventSolved(){

    }
    public void houseEventReport() {
        System.out.println("--Events since last report--");
        for (String event : this.events.keySet()){
            System.out.println(this.events.get(event)+" handeled succesfuly "+event);
        }
        this.events.clear();
    }

    public void activityAndUsageReport() {

    }

    public void consuptionReport() {

    }

}
