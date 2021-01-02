package general;

public abstract class Observer {

    public Observer(Observable observable) {
        observable.attach(this);
    }

    public abstract void update();
}
