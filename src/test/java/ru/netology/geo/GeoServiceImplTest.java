package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Location;
import ru.netology.i18n.LocalizationService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @ParameterizedTest
    @CsvSource({"172.0.32.11, Moscow, RUSSIA",
            "96.44.183.149, New York, USA"})
    void byIp(String ip, String city, String country) {
        // Создаем объект геосервиса
        GeoServiceImpl geoService = new GeoServiceImpl();
        // Получаем локацию через метод по IP из параметров теста
        Location location = geoService.byIp(ip);
        // Сравниваем полученную локацию с параметрами теста
        assertThat(city, is(location.getCity()));
        assertThat(country, is(location.getCountry().toString()));
    }

    /**
     * Тест выбрасываемого исключения
     */
    @Test
    void byCoordinates() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        assertThrows(RuntimeException.class, () -> geoService.byCoordinates(123456, 456789));
    }
}