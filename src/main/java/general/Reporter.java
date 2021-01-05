package general;

import animals.Animal;
import appliance.Appliance;
import appliance.ApplianceFasada;
import equipment.Equipment;
import people.Person;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Reporter {
    House house;
    HashMap<String, String> events = new HashMap<>();   // ("#event", "#who managed to get rid of the event")
    HashMap<String, HashMap<String, Integer>> activities = new HashMap<>();
    private File file;
    private FileWriter fileWriter;

    public Reporter(House house) {
        this.house = house;
        file = new File("report.txt");
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            fileWriter = new FileWriter("report.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        for (Appliance app : house.getApplianceFasada()
                                  .getAppliances()) {
            app.report(this);
        }
        for (Equipment eq : house.getEquipmentFasada()
                                 .getEquipment()) {
            eq.report(this);
        }
        for (Animal ani : house.getAnimalFasada()
                               .getAnimalMap()
                               .values()) {
            ani.report(this);
        }
        for (Person per : house.getPeopleFasada()
                               .getPersonMap()
                               .values()) {
            per.report(this);
        }
    }

    public void houseConfigurationReport() {
        System.out.println("--House Configuration Report--\nHouse:");
        try {
            fileWriter.write("--House Configuration Report--\nHouse:\n");
            for (Room room : house.getRoomFasada()
                                  .getRoomLinkedList()) {
                System.out.println("\t" + room.getName() + ":");
                fileWriter.write("\t" + room.getName() + ":\n");
                for (Tickable item : room.getPropriets()) {
                    System.out.println("\t\t" + item.getClass()
                                                    .getSimpleName());
                    fileWriter.write("\t\t" + item.getClass()
                                                  .getSimpleName() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eventCatch(String event, String entity) {      // old eventSolved (didn't make sense) /// changed everywhere
        if (this.events.containsKey(event)) {
            this.events.replace(event, entity);
        } else {
            this.events.put(event, entity);
        }
    }

    public void houseEventReport() {
        System.out.println("--Events since last report--");
        try {
            fileWriter.write("--Events since last report--\n");
            for (String event : this.events.keySet()) {
                String handler = this.events.get(event);
                if (handler != null) {
                    System.out.println(event + " handeled succesfuly by " + this.events.get(event));
                    fileWriter.write(event + " handeled succesfuly by " + this.events.get(event) + "\n");
                } else {
                    System.out.println(event + " is pending");
                    fileWriter.write(event + " is pending\n");
                }
            }
            this.events.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        try {
            fileWriter.write("--Activities since last report--\n");
            for (String creature : this.activities.keySet()) {
                for (String activity : this.activities.get(creature)
                                                      .keySet()) {
                    System.out.println(creature + " " + activity + " " + this.activities.get(creature)
                                                                                        .get(activity) + "x");
                    fileWriter.write(creature + " " + activity + " " + this.activities.get(creature)
                                                                                      .get(activity) + "x\n");
                }
            }
            this.activities.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void consuptionReport() {
        System.out.println("--Consuption since last report--");
        try {
            fileWriter.write("--Consuption since last report--\n");
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
                    fileWriter.write(app.getClass()
                                        .getSimpleName() + " used " + electricity + " electricity and " + water + " water." +
                            "\n\tTotal price: " + consumed + "\n");
                } else {
                    int consumed = electricity * elCost;
                    System.out.println(app.getClass()
                                          .getSimpleName() + " used " + electricity + " electricity " +
                            "\n\tTotal price: " + consumed);
                    fileWriter.write(app.getClass()
                                        .getSimpleName() + " used " + electricity + " electricity " +
                            "\n\tTotal price: " + consumed + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endReport() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
