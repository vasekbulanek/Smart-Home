package appliance.workItems;

import appliance.Appliance;
import general.Fasada;
import people.Person;

public interface Work {
    boolean work(Person person);

    boolean work(Appliance appliance, Person person);

    Fasada.allClasses need();
}
