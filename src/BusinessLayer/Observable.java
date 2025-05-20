package BusinessLayer;

public interface Observable {
    public void addObserver(Observer var1);

    public void removeObserver(Observer var1);

    public void tickObservers();
}

