package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @ParameterizedTest
    @CsvSource({"RUSSIA, Добро пожаловать ",
            "USA, Welcome"})
    void locale(String country, String answer) {
        // Задаем страну по параметрам теста
        Country enumCountry;
        if (country.equals("RUSSIA")) {
            enumCountry = Country.RUSSIA;
        } else if (country.equals("USA")) {
            enumCountry = Country.USA;
        } else {
            enumCountry = Country.USA;
        }
        assertThat(answer, is(new LocalizationServiceImpl().locale(enumCountry)));
    }
}