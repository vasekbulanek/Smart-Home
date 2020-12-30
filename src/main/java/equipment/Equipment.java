package equipment;

public abstract class Equipment {
    public boolean free;
    public boolean functionality;

    public Equipment() {
        free = true;
        functionality = true;
    }

    public boolean use() {
        if (free) {
            free = false;
            return true;
        } else return false;
    }

    private void breakDown() {
        functionality = false;
    }

    public void repair() {
        functionality = true;
    }

    protected abstract void report();

    protected abstract void tick();


}
