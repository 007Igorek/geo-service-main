import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.*;

public class GeoServiceImplTest {
    private GeoService geoService;

    @BeforeEach
    void setUp() {
        geoService = new GeoServiceImpl();
    }

    @Test
    void byIpTest_RU_IP() {
        Location actual_location = geoService.byIp("172.123.12.19");

        Assertions.assertEquals(
                Country.RUSSIA,
                actual_location.getCountry()
        );
    }

    @Test
    void byIpTest_US_IP() {
        Location actual_location = geoService.byIp("96.123.12.19");

        Assertions.assertEquals(
                Country.USA,
                actual_location.getCountry()
        );
    }

    @Test
    void byIpTest_LOCALHOST() {
        Location actual_location = geoService.byIp("127.0.0.1");

        Assertions.assertNull(
                actual_location.getCountry()
        );
    }

    @Test
    void byIpTest_NULL_IP() {
        Location actual_location = geoService.byIp("200.0.32.11");

        Assertions.assertNull(
                actual_location
        );
    }

}
