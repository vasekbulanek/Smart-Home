package animals;

public abstract class Animal {
    int hunger;
    int mood;
    int health;
    boolean sleeping;

    public abstract void play();

    protected void eat() {
        hunger = 0;
    }

    protected void sleep() {
        sleeping = true;
    }

    protected void wakeUp() {
        sleeping = false;
    }

    public abstract void tick();

    protected abstract void report();

}
