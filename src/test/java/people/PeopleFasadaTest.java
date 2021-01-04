package people;

import general.Fasada;
import general.House;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;


public class PeopleFasadaTest {

    public PeopleFasada peopleFasada;

    @Before
    public void runBeforeEachTest(){
        House house = new House();
        peopleFasada = new PeopleFasada(house, "src/main/resources/init.json");
        System.out.println("prepared");
    }

    /**
     * Tests are based on current init.json
     * if is a mistake there, tests fail
     */
    @Test
    public void testGetByName() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/init.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject people = (JSONObject) jsonObject.get("people");
            for (Object key:people.keySet()) {
                String name = key.toString();
                System.out.println(name);
                Assert.assertNotNull(peopleFasada.getByName(name));
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGetByType() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/init.json"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject people = (JSONObject) jsonObject.get("people");
            for (Object x:people.values()) {
                String type = x.toString();
                Assert.assertNotNull(peopleFasada.getByType(convert(type)));
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
    public static Fasada.allClasses convert(String string) {
        for (Fasada.allClasses a: Fasada.allClasses.values()) {
            System.out.println(a + " "+string.toLowerCase(Locale.ROOT));
            if(a.toString().contains(string.toLowerCase(Locale.ROOT)))return a;
        }
        return null;
    }
}