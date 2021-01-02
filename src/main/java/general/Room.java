package general;

import people.Person;

import java.util.LinkedList;

public class Room {
    String name;
    LinkedList<Tickable> propriets;
    House house;

    public Room(String name, House house) {
        this.name = name;
        propriets = new LinkedList<>();
        this.house=house;
    }



    public void addPropriet(Tickable tickable, Room old) {
        if(old!=null) old.deletePropriet(tickable);
        propriets.add(tickable);
        tickable.place(this);
    }

    protected void deletePropriet(Tickable tickable) {
        if (propriets.contains(tickable)) propriets.remove(tickable);
    }

    public String getName() {
        return name;
    }

    public LinkedList<Tickable> getPropriets() {
        return propriets;
    }

    public LinkedList<Person> getPeople(){
        LinkedList<Person> ret = new LinkedList<>();
        for (Tickable t:propriets) {
            if (t.toString().contains("people.")){
                ret.add((Person) t);
            }
        }
        return ret;
    }

    public void printContent() {
        System.out.println(name + propriets.toString());
    }
}

