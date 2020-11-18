package people;

public abstract class Person {
    int hunger;
    int mood;
    int health;
    boolean sleeping;

    protected abstract void useAppliance();
    protected void eat(){
        hunger=0;
    }
    protected void sleep(){
        sleeping=true;
    }
    protected void wakeUp(){
        sleeping=false;
    }
    protected void sport(){

    }
}
