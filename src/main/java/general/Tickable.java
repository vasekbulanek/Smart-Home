package general;

public interface Tickable {
    void tick();

    void report(Reporter reporter);

    void place(Room room);
    Room getRoom();
}
