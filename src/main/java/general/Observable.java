package general;

import java.util.LinkedList;

public class Observable {
    LinkedList<Observer>observers;
    public Observable(){
        observers=new LinkedList<>();
    }
    public void attach(Observer observer){
        observers.add(observer);
    }
    void change(){
        for (Observer o :observers) {
            o.update();
        }
    }
}
