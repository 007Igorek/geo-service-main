import org.junit.jupiter.api.Test;

import ru.netology.entity.Country;
import ru.netology.i18n.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceImplTest {
    private LocalizationService localizationService;

    @Test
    public void testLocale_Russia_ReturnsRussianMessage() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(Country.RUSSIA);
        assertEquals("Добро пожаловать", result);
    }

    @Test
    public void testLocale_Germany_ReturnsDefaultMessage() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(Country.GERMANY);
        assertEquals("Welcome", result);
    }

    @Test
    public void testLocale_USA_ReturnsDefaultMessage() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(Country.USA);
        assertEquals("Welcome", result);
    }

    @Test
    public void testLocale_Brazil_ReturnsDefaultMessage() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(Country.BRAZIL);
        assertEquals("Welcome", result);
    }
}
