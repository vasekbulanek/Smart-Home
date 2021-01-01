package people;

import general.House;
import general.Room;
import general.Tickable;

import java.util.LinkedList;
import java.util.Random;

public class Baby extends Person {
    private boolean diaper;
    public Baby(House house) {
        super(house);
        diaper=false;
    }

    private void hungry(){
        hunger++;
        if(hunger>6 && !sleeping){
            house.getPeopleFasada().getByType("Mother").addPersonRequest(this);
        }
        if(hunger>8){
            sleeping=false;
            house.getPeopleFasada().getByType("Mother").addPersonRequest(this);
        }
    }
    private void poop(){
        if(diaper && !sleeping){
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
                sleeping=true;
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


    @Override
    protected void useAppliance() {

    }
    public int getHunger(){
        return hunger;
    }
    public boolean getDiaper(){
        return diaper;
    }
}
