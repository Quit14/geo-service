package entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocationTest {
    private LocalizationServiceImpl locService;

    @BeforeEach
    void setUp() {
        locService = new LocalizationServiceImpl();
    }

    @Test
    @DisplayName("Тест: иностранное приветствие")
    void localeDefault() {
        Assertions.assertEquals("Welcome", locService.locale(Country.GERMANY));
    }

    @Test
    @DisplayName("Тест: местное приветствие")
    void localeRussia() {
        Assertions.assertEquals("Добро пожаловать", locService.locale(Country.RUSSIA));
    }
}
