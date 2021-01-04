package people;

import animals.Animal;
import appliance.Boiler;
import appliance.Phone;
import appliance.Television;
import appliance.workItems.Work;
import general.*;

import java.util.Random;

public class Girl extends Person  {

    public Girl(House house) {
        super(house);
        personType= Fasada.allClasses.girl;
    }

    @Override
    public void tick() {
        super.tick();
        if(request.allRequests()>0){
            if(request.hasTo(Request.Typ.work)>0){
                Work r = request.getWork();
                Fasada.allClasses name = r.need();
                if(name!=null)r.work(house.getApplianceFasada().getByType(name), this);
                else r.work();
                return;
            }
            if(request.hasTo(Request.Typ.person)>0){
                solvePerson(request.getPerson());
                return;
            }
            if(request.hasTo(Request.Typ.animal)>0){
                Animal animal = request.getAnimal();
                animal.getRoom().addPropriet(this, room);
                if (animal.getHunger()>10)animal.feed();
                if(!animal.isSleeping()){
                    animal.play();
                    delay();
                }
                return;
            }
        }
        Random random = new Random();
        if(random.nextBoolean()){
            Phone phone= (Phone) house.getApplianceFasada().getByType(Fasada.allClasses.phone);
            if(phone!=null)phone.use(this);
            else {
                Television television = (Television) house.getApplianceFasada().getByType(Fasada.allClasses.television);
                if(television!=null)television.use(this);
            }
            return;
        }
        sport();
    }

    @Override
    public void report() {

    }


    public void addRepairableRequest(Repairable repairable) {
        Father father = (Father) house.getPeopleFasada().getByType(Fasada.allClasses.father);
        if(father!=null){
            father.addRepairableRequest(repairable);
        }
        else {
            Mother mother = (Mother) house.getPeopleFasada().getByType(Fasada.allClasses.mother);
            if(mother!=null){
                mother.addRepairableRequest(repairable);
            }
        }
    }
    private void solvePerson(Person person){
        if(person instanceof Baby)solvePerson((Baby) person);
    }
    private void solvePerson(Baby baby){
        baby.getRoom().addPropriet(this, null);
        if(baby.getHunger()>4)baby.eating();
        if (baby.getDiaper())baby.diapering();
    }
}