package people;

import general.Fasada;
import general.House;
import general.Tickable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class PeopleFasada extends Fasada {
    Map<String, Person> personMap;
    House house;
    private PeopleIterator peopleIterator;


    public PeopleFasada(House house, String initFile) {
        super(house, initFile);
        personMap = new HashMap<>();
        JSONParser parser = new JSONParser();
        this.house = house;
        try {
            Object obj = parser.parse(new FileReader(initFile));
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject people = (JSONObject) jsonObject.get("people");
            for (Object key : people.keySet()) {
                String name = key.toString();
                String type = people.get(key)
                                    .toString();
                System.out.println("Hi, I am " + name + " and I am " + type);
                switch (type) {
                    case ("Father"):
                        Father father = Father.getInstance(house, name);
                        if (!personMap.containsValue(father)) personMap.put(name, father);
                        break;
                    case ("Mother"):
                        Mother mother = Mother.getInstance(house, name);
                        if (!personMap.containsValue(mother)) personMap.put(name, mother);
                        break;
                    case ("Boy"):
                        personMap.put(name, new Boy(house, name));
                        break;
                    case ("Girl"):
                        personMap.put(name, new Girl(house, name));
                        break;
                    case ("Baby"):
                        personMap.put(name, new Baby(house, name));
                        break;
                    default:
                        System.out.println("There is unknown person type " + type + ". Check init.json, please.");
                }
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendTicks() {
        for (Person person : personMap.values()) {
            person.tick();
        }

    }

    public Map<String, Person> getPersonMap() {
        return personMap;
    }

    public Person getByName(String name) {
        for (String key : personMap.keySet()) {
            if (name.equals(key)) {
                return personMap.get(key);
            }
        }
        return null;
    }

    public Person getByType(allClasses personType) { // I am not sure if it is the best way, but it works
        LinkedList<Person> people = new LinkedList<>();
        for (Person values : personMap.values()) {
            if (values.getPersonType() == personType) {
                people.add(values);
            }
        }
        if (people.size() > 0) {
            Random random = new Random();
            return people.get(random.nextInt(people.size() + 2) % people.size());
        }
        return getPeopleIterator().next();
    }

    public Person getNextByType(allClasses type, int hash) {
        boolean found = false;
        for (Person key : personMap.values()) {
            if (!found && key.hashCode() == hash) found = true;
            else if (found && key.getPersonType() == type) {
                return key;
            }
        }
        return getPeopleIterator().next();
    }

    public void checkRooms() {
        for (Person p : personMap.values()) {
            if (p.getRoom() == house.getRoomFasada()
                                    .getOutside()) {
                house.getRoomFasada()
                     .getRoomLinkedList()
                     .get(0)
                     .addPropriet(p, p.room);
            }
        }
    }

    public Person getRandom() {
        Random r = new Random();
        Person person = (Person) personMap.values()
                                          .toArray()[r.nextInt(personMap.size())];
        return person;
    }

    public int getSize() {
        return personMap.size();
    }

    public PeopleIterator getPeopleIterator() {
        if (peopleIterator==null){
            peopleIterator = new PeopleIterator();
        }
        return peopleIterator;
    }

    public class PeopleIterator implements Iterator{
        private int current;
        private final Object[] people;
        private PeopleIterator() {
            current = -1;
            people = personMap.values().toArray();
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Person next() {
            current = (current+1)%personMap.size();
            return (Person) people[current];
        }
    }
}
