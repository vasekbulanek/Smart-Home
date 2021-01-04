package general;

import people.Person;

public interface Repairable extends Tickable {
    void repair(Person person);

    boolean use(Person person);
}
