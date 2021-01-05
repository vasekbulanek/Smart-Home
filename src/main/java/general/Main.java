package general;

public class Main {
    public static void main(String[] args) {
        House house = new House();
        house.reporter.houseConfigurationReport();
        for (int i = 0; i<24*30; i++) {
            house.run();
            if (i % 24 == 0) {
                house.reporter.activityAndUsageReport();

            }
        }
        house.reporter.consuptionReport();
        house.reporter.endReport();

    }
}
