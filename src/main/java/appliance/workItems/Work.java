package appliance.workItems;

import appliance.Appliance;
import people.Person;

public interface Work {
     boolean work();
     boolean work(Appliance appliance, Person person);
     String need();
}
