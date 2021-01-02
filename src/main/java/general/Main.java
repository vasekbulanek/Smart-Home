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
}
