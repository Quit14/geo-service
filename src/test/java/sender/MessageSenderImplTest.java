package sender;

import geo.GeoServiceImplTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class MessageSenderImplTest {

    @Mock
    private GeoServiceImpl geoService;
    private static final String MOSCOW_IP = "172.0.32.11";
    private static final String NEW_YORK_IP = "96.44.183.149";

    @Mock
    private LocalizationServiceImpl localizationService;

    private MessageSenderImpl mSender;


    @BeforeEach
    void setUp() {
        mSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    @DisplayName("Тест отправки сообщения с московского ip")
    void send_whenIpIsMoscow() {
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, MOSCOW_IP);
        Location location = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        Mockito.when(geoService.byIp(Mockito.eq(MOSCOW_IP))).thenReturn(location);
        Mockito.when(localizationService.locale(Mockito.any())).thenReturn("Добро пожаловать");
        Assertions.assertEquals("Добро пожаловать", mSender.send(headers));
    }

    @Test
    @DisplayName("Тест отправки сообщения с ip Нью-Йока")
    void send_whenIpIsNewYork() {
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, NEW_YORK_IP);
        Location location = new Location("New York", Country.USA, " 10th Avenue", 32);
        Mockito.when(geoService.byIp(Mockito.eq(NEW_YORK_IP))).thenReturn(location);
        Mockito.when(localizationService.locale(Mockito.any())).thenReturn("Welcome");
        Assertions.assertEquals("Welcome", mSender.send(headers));
    }

    @Test
    @DisplayName("Тест с пустым ip")
    void send_whenIpIsEmpty() {
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "");
        Mockito.when(localizationService.locale(Mockito.any())).thenReturn("Welcome");
        Assertions.assertEquals("Welcome", mSender.send(headers));
    }

    @Test
    @DisplayName("Тест с нулевым ip")
    void send_whenIpIsNull() {
        Map<String, String> headers = new HashMap<>();
        Mockito.when(localizationService.locale(Mockito.any())).thenReturn("Welcome");
        Assertions.assertEquals("Welcome", mSender.send(headers));
    }
}
