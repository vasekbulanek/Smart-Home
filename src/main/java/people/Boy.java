package people;

import animals.Animal;
import general.Fasada;
import general.House;

public class Boy extends Person  {

    public Boy(House house, String name) {
        super(house, name);
        personType= Fasada.allClasses.boy;
    }


    @Override
    public void tick() {
        super.tick();
        if(request.allRequests()>0){
            if(request.hasTo(Request.Typ.repairable)>0){
                request.getRepairable().repair(this);
                return;
            }
            if(request.hasTo(Request.Typ.work)>0){
                workSolve();
                return;
            }
            if(request.hasTo(Request.Typ.animal)>0){
                Animal animal = request.getAnimal();
                animal.getRoom().addPropriet(this, room);
                if (animal.getHunger()>10)animal.feed();
                if(!animal.isSleeping()){
                    animal.play();
                }
                return;
            }

        }
        else sport();
    }

    @Override
    public void report() {

    }


    public void addPersonRequest(Person person) {
        Mother mother = (Mother) house.getPeopleFasada().getByType(Fasada.allClasses.mother);
        if(mother!=null){
            mother.addPersonRequest(person);
        }
        else {
            Girl girl = (Girl) house.getPeopleFasada().getByType(Fasada.allClasses.girl);
            if(girl!=null){
                girl.addPersonRequest(person);
            }
        }
    }
}

