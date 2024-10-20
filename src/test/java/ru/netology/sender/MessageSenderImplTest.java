package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {

    @ParameterizedTest
    @CsvSource({"172.0.32.11, Moscow, RUSSIA, Lenina, 15, Добро пожаловать ",
            "96.44.183.149, New York, USA,  10th Avenue, 32, Welcome"})
    void send(String ip, String city, String country, String street, int building, String answer) {

        // Задаем страну по параметрам теста, как-то по-другому не смогла придумать
        Country enumCountry;
        if (country.equals("RUSSIA")) {
            enumCountry = Country.RUSSIA;
        } else if (country.equals("USA")) {
            enumCountry = Country.USA;
        } else {
            enumCountry = Country.USA;
        }

        // Создаем заглушку для интерфейса GeoService
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.anyString())).
                thenReturn(new Location(city, enumCountry, street, building));

        // Создаем заглушку для интерфейса LocalizationService
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(enumCountry)).thenReturn(answer);

        // Вызываем метод send, получаем результат, который надо сравнить
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String result = messageSender.send(headers);

        assertEquals(answer, result);
    }
}