package general;

import java.util.Random;

public class Weather implements Tickable {
    private int temperature;
    private static int maxTemperature;
    private static int minTemperature;
    private clouds sky;

    private enum clouds {
        sunny, clouds, overcast, splashes, rains, snow
    }

    //Todo add observers
    //Todo test Weather
    public Weather() {
        temperature = 15;
        maxTemperature = 35;
        minTemperature = -10;
        sky = clouds.sunny;
    }

    private void changeTemperature() {
        Random random = new Random();
        int number = random.nextInt(7) - 3;// change of temperature should not be too big
        if (number + temperature < minTemperature) {
            temperature++;
        } else if (number + temperature > maxTemperature) {
            temperature--;
        } else {
            temperature += number;
        }
    }

    private void changeSky() {
        Random random = new Random();
        int number = random.nextInt(3) - 1;
        if (number == 0) return;
        switch (sky) {
            case rains:
                if (temperature < 0) sky = clouds.snow;
                else sky = clouds.splashes;
                break;
            case splashes:
                if (number < 0) sky = clouds.overcast;
                else if (temperature < 0) sky = clouds.snow;
                else sky = clouds.splashes;
                break;
            case overcast:
                if (number < 0) sky = clouds.clouds;
                else {
                    temperature -= 2;
                    if (temperature < 0) sky = clouds.snow;
                    else sky = clouds.splashes;
                }
                break;
            case clouds:
                if (number < 0) {
                    sky = clouds.sunny;
                    temperature += 2;
                } else sky = clouds.overcast;
                return;
            case sunny:
                if (number < 0) sky = clouds.clouds;
                return;
            case snow:
                if (number < 0) sky = clouds.overcast;
        }
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public void tick() {
        changeTemperature();
        changeSky();
    }

    @Override
    public void report() {

    }

    @Override
    public void place(Room room) {
    }

    @Override
    public Room getRoom() {
        return null;
    }
}
