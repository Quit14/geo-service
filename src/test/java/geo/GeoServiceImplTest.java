package geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.LinkedList;
import java.util.Locale;

public class GeoServiceImplTest {

    private GeoServiceImpl geoTest;
    private static final String LOCALHOST = "127.0.0.1";
    private static final String MOSCOW_IP = "172.0.32.11";
    private static final String NEW_YORK_IP = "96.44.183.149";

    @BeforeEach
    public void setUp() {
        geoTest = new GeoServiceImpl();
    }

    @Test
    @DisplayName("Тест определения локации по местному ip")
    void locateByLocalIp() {
        Location expectedLocation = new Location(null, null, null, 0);
        Assertions.assertEquals(expectedLocation, geoTest.byIp(LOCALHOST));
    }

    @Test
    @DisplayName("Тест определения локации по московскому ip")
    void locateByMoscowIp() {
        Location expectedLocation = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        Assertions.assertEquals(expectedLocation, geoTest.byIp(MOSCOW_IP));
    }

    @Test
    @DisplayName("Тест определения локации по ip Нью-Йорка")
    void locateByNewYorkIp() {
        Location expectedLocation = new Location("New York", Country.USA, " 10th Avenue", 32);
        Assertions.assertEquals(expectedLocation, geoTest.byIp(NEW_YORK_IP));
    }

    @Test
    @DisplayName("Тест определения локации по российскому ip")
    void locateByRussianIp() {
        String ip = "172.";
        Location expectedLocation = new Location("Moscow", Country.RUSSIA, null, 0);
        Assertions.assertEquals(expectedLocation, geoTest.byIp(ip));
    }

    @Test
    @DisplayName("Тест определения локации по американскому ip")
    void locateByAmericanIp() {
        String ip = "96.";
        Location expectedLocation = new Location("New York", Country.USA, null, 0);
        Assertions.assertEquals(expectedLocation, geoTest.byIp(ip));
    }

    @Test
    @DisplayName("Тест ошибочного ip")
    void locateByWrongIp() {
        Assertions.assertEquals(null, geoTest.byIp(" "));
    }
}

