package general;

import appliance.Appliance;
import appliance.ApplianceFasada;

import java.util.HashMap;
import java.util.Map;

public class Reporter {
    House house;
    HashMap<String, String> events = new HashMap<>();   // ("#event", "#who managed to get rid of the event")
    HashMap<String, HashMap<String, Integer>> activities = new HashMap<>();

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

    public void eventSolved(String event, String entity) {      // more like eventCatch (have to change everywhere)
        if (this.events.containsKey(event)) {
            this.events.replace(event, entity);
        } else {
            this.events.put(event, entity);
        }
    }

    public void houseEventReport() {
        System.out.println("--Events since last report--");
        for (String event : this.events.keySet()) {
            String handler = this.events.get(event);
            if (handler != null) {
                System.out.println(event + " handeled succesfuly by " + this.events.get(event));
            } else {
                System.out.println(event + " is pending");
            }
        }
        this.events.clear();
    }

    public void activityCatch(String creature, String activity) {   // too complicated probably
        HashMap<String, Integer> insideMap;
        if (this.activities.containsKey(creature)) {
            if (this.activities.get(creature)
                               .containsKey(activity)) {
                int value = this.activities.get(creature)
                                           .get(activity);
                this.activities.get(creature)
                               .replace(activity, value + 1);
            } else {
                insideMap = new HashMap<String, Integer>();
                insideMap.put(activity, 1);
                this.activities.replace(creature, insideMap);
            }
        } else {
            insideMap = new HashMap<String, Integer>();
            insideMap.put(activity, 1);
            this.activities.put(creature, insideMap);
        }
    }

    public void activityAndUsageReport() {
        System.out.println("--Activities since last report--");
        for (String creature : this.activities.keySet()) {
            for (String activity : this.activities.get(creature)
                                                  .keySet()) {
                System.out.println(creature+" spent "+this.activities.get(creature).get(activity)+" hours "+activity);
            }
        }
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
                System.out.println(app.getClass()
                                      .getSimpleName() + " used " + electricity + " electricity and " + water + " water." +
                        "\n\tTotal price: " + consumed);
            } else {
                int consumed = electricity * elCost;
                System.out.println(app.getClass()
                                      .getSimpleName() + " used " + electricity + " electricity " +
                        "\n\tTotal price: " + consumed);
            }
        }
    }

}
