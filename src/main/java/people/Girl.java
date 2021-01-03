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
    }

    @Override
    public void tick() {
        if (activity==longActivity.sport){
            Boiler boiler =(Boiler) house.getApplianceFasada().getByType("Boiler");
            if(boiler!=null)boiler.use();
            delay();
        }
        super.tick();
        if(request.allRequests()>0){
            if(request.hasTo(Request.Typ.work)>0){
                Work r = request.getWork();
                String name = r.need();
                if(name!=null)r.work(house.getApplianceFasada().getByType(name));
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
            Phone phone= (Phone) house.getApplianceFasada().getByType("Phone");
            if(phone!=null)phone.use();
            else {
                Television television = (Television) house.getApplianceFasada().getByType("Television");
                if(television!=null)television.use();
            }
            return;
        }
        sport();
    }

    @Override
    public void report() {

    }


    @Override
    protected void useAppliance() {

    }

    public void addRepairableRequest(Repairable repairable) {
        Father father = (Father) house.getPeopleFasada().getByType("Father");
        if(father!=null){
            father.addRepairableRequest(repairable);
        }
        else {
            Mother mother = (Mother) house.getPeopleFasada().getByType("Mother");
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