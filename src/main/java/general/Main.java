package general;

public class Main {
    public static void main(String[] args) {
        House house = new House();
        //house.reporter.houseConfigurationReport();
        for (int i = 0; i<24*2+1; i++) {
            house.run();
            if (i % 24 == 0) {
                house.reporter.activityAndUsageReport();
                house.reporter.houseEventReport();
            }
        }
        //house.reporter.consuptionReport();
        //house.reporter.endReport();

    }
}
