package general;

import java.util.Scanner;

public class Main {
    // Controls
    private static void help() {
        System.out.println("h - HELP\n" +
                "#any whole number - run simulation for that many (virtual) hours\n" +
                "g - configuration report\n" +
                "c - consuption report\n" +
                "e - event report\n" +
                "a - activity and usage report\n" +
                "q - end of simulation");
    }

    public static void main(String[] args) {
        House house = new House();

        Scanner Input = new Scanner(System.in);
        String action = "0";
        help();
        do {
            try {
                action = Input.next();

                if (action.equalsIgnoreCase("q")) {
                    System.out.println("Have a great day! :)");
                } else if (action.equals("h")) {
                    help();
                } else if (action.equals("g")) {
                    house.reporter.houseConfigurationReport();
                } else if (action.equals("c")) {
                    house.reporter.consuptionReport();
                } else if (action.equals("e")) {
                    house.reporter.houseEventReport();
                } else if (action.equals("a")) {
                    house.reporter.activityAndUsageReport();
                } else if (Integer.parseInt(action) > 0) {
                    System.out.println("Running house for " + action + " hours");
                    for (int i = 0; i < Integer.parseInt(action); i++) {
                        house.run();
                    }
                }
            } catch (NumberFormatException r) {
                System.out.println("Try something different -> q for help");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } //Continue the loop while Number is not equal to Q
        while (!action.equalsIgnoreCase("q"));
        house.reporter.endReport();

    }
}
