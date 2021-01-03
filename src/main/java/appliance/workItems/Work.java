package appliance.workItems;

import appliance.Appliance;

public interface Work {
     boolean work();
     boolean work(Appliance appliance);
     String need();
}
