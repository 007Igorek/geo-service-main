import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.netology.entity.*;
import ru.netology.geo.*;
import ru.netology.i18n.*;
import ru.netology.sender.*;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class MessageSenderImplTest {
    @Mock
    private GeoService geoService;
    @Mock
    private LocalizationService localizationService;
    @Mock
    private MessageSender messageSender;


    @Test
    void send_when_RU_IP_getRussianText() {
        String ipAddress = "172.123.12.19";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);

        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        String result = messageSender.send(headers);

        Assertions.assertEquals("Добро пожаловать", result);
    }

    @Test
    void send_when_US_IP_getEnglishText() {
        String ipAddress = "96.123.12.19";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);

        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(new Location("New York", Country.USA, null,  0));
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        String result = messageSender.send(headers);

        Assertions.assertEquals("Welcome", result);
    }

    @Test
    void send_MOSCOW_IP() {
        String ipAddress = "172.123.12.19";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);

        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        String result = messageSender.send(headers);

        Assertions.assertEquals("Добро пожаловать", result);
    }

    @Test
    void send_not_NY_IP() {
        String ipAddress = "96.123.12.19";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);

        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        String result = messageSender.send(headers);

        Assertions.assertEquals("Welcome", result);
    }
}
