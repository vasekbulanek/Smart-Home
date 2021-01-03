package general;

import appliance.Appliance;
import appliance.ApplianceFasada;

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

    public void newEvent(String event) {
        this.events.put(event, "pending");
    }

    public void eventSolved(String event, String entity) {
        this.events.replace(event, entity);
    }

    public void houseEventReport() {
        System.out.println("--Events since last report--");
        for (String event : this.events.keySet()) {
            System.out.println(this.events.get(event) + " handeled succesfuly " + event);
        }
        this.events.clear();
    }

    public void activityAndUsageReport() {

    }

    public void consuptionReport() {
        System.out.println("--Consuption since last report--");
        ApplianceFasada appFas = house.getApplianceFasada();
        int elCost = appFas.getElectricityPrice();
        int watCost = appFas.getWaterPrice();
        for (Appliance app : appFas.getAppliances()) {
            int electricity = app.getUsedElectricity();
            int water = app.getUsedWater();
            if (water > 0) {
                int consumed = electricity * elCost + water * watCost;
                System.out.println(app.getClass().getSimpleName()+" used "+electricity+" electricity and "+water+" water." +
                        "\n\tTotal price: "+consumed);
            }
            else {
                int consumed = electricity * elCost;
                System.out.println(app.getClass().getSimpleName()+" used "+electricity+" electricity "+
                        "\n\tTotal price: "+consumed);
            }
        }
    }

}
