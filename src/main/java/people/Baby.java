package people;

import animals.Animal;
import appliance.workItems.Work;
import general.House;
import general.Repairable;

import java.util.Random;

public class Baby extends Person {
    private boolean diaper;
    public Baby(House house) {
        super(house);
        diaper=false;
    }

    private void hungry(){
        hunger++;
        if(hunger>6 && activity!=longActivity.sleep){
            house.getPeopleFasada().getByType("Mother").addPersonRequest(this);
        }
        if(hunger>8){
            activity=longActivity.no;
            house.getPeopleFasada().getByType("Mother").addPersonRequest(this);
        }
    }
    private void poop(){
        if(diaper && activity!=longActivity.sleep){
            house.getPeopleFasada().getByType("Mother").addPersonRequest(this);
            return;
        }
        Random random = new Random();
        int number = random.nextInt(5) ;
        if(hunger<=6 && number==1){
            diaper=true;
        }
    }
    protected void diapering(){
        diaper=false;
    }

    protected void eating(){
        hunger=0;
    }
    public boolean Gosleep(){
        if(hunger<4){
            Random random = new Random();
            int number = random.nextInt(3) ;
            if (number==1){
                activity=longActivity.sleep;
                return true;
            }
        }
        return false;
    }
    @Override
    public void tick() {
        house.getPeopleFasada().getByType("Mother").addPersonRequest(this);
        poop();
        hungry();
        Gosleep();
    }

    @Override
    public void report() {

    }


    public int getHunger(){
        return hunger;
    }

    public boolean getDiaper(){
        return diaper;
    }

    public void addPersonRequest(Person person) {
        house.getPeopleFasada().getByType("Mother").addPersonRequest(person);
    }

    public void addAnimalRequest(Animal animal) {
        request.addAnimal(animal);
        house.getPeopleFasada().getByType("Mother").addAnimalRequest(animal);
    }

    public void addRepairableRequest(Repairable repairable) {
        house.getPeopleFasada().getByType("Mother").addRepairableRequest(repairable);
    }

    public void addWorkRequest(Work work) {
        house.getPeopleFasada().getByType("Mother").addWorkRequest(work);
    }
}
