package general;

public class Main {
    public static void main(String[] args) {
        House house = new House();
        house.run();
        house.run();
        house.run();
        Reporter reporter = new Reporter(house);
        reporter.houseConfigurationReport();
    }
    // Todo: Do something about the rooms. It should be chengeable from init.json
    // Is it worse to have Czech comments, or have them in my poor English?
}
