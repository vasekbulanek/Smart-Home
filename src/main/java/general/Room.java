package general;

import java.util.LinkedList;

public class Room {
    String name;
    LinkedList<Tickable> propriets;

    public Room(String name) {
        this.name = name;
        propriets = new LinkedList<>();
    }

    public void addPropriet(Tickable tickable) {
        propriets.add(tickable);
        tickable.place(this);
    }

    public void deletePropriet(Tickable tickable) {
        if (propriets.contains(tickable)) propriets.remove(tickable);
    }

    public String getName() {
        return name;
    }

    public LinkedList<Tickable> getPropriets() {
        return propriets;
    }

    public void printContent() {
        System.out.println(name + propriets.toString());
    }
}
