package people;

import appliance.Appliance;
import general.Fasada;
import general.House;

import java.util.Random;


public class Father extends Person  {
    private static Father singleton = null;

    private Father(House house) {
        super(house);
        personType= Fasada.allClasses.father;
    }

    public static Father getInstance(House house) {
        if (singleton == null) {
            singleton = new Father(house);
            return singleton;
        } else {
            System.out.println("This house already has its father. No new will be created.");
            return singleton;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(request.allRequests()>0){
            if(request.hasTo(Request.Typ.work)>0){
                if(house.getPeopleFasada().getByType(Fasada.allClasses.girl)!=null){
                    house.getPeopleFasada().getByType(Fasada.allClasses.girl).addWorkRequest(request.getWork());
                }
                if(house.getPeopleFasada().getByType(Fasada.allClasses.boy)!=null){
                    house.getPeopleFasada().getByType(Fasada.allClasses.boy).addWorkRequest(request.getWork());
                }
                else {
                    workSolve();
                    return;
                }
            }
            if(request.hasTo(Request.Typ.repairable)>0){
                if(request.hasTo(Request.Typ.repairable)>2){
                    if (house.getPeopleFasada().getByType(Fasada.allClasses.boy)!=null){
                        house.getPeopleFasada().getByType(Fasada.allClasses.boy).addRepairableRequest(request.getRepairable());
                    }
                }
                request.getRepairable().repair(this);
                return;
            }
            if(request.hasTo(Request.Typ.animal)>0){
                request.getAnimal().feed();
            }
        }
        Random random = new Random();
        if(random.nextBoolean()){
            Appliance a = house.getApplianceFasada().getByType(Fasada.allClasses.television);
            if(a!=null){
                a.use(this);
                return;
            }
            sport();
        }
    }

    @Override
    public void report() {

    }


}