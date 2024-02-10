package ru.kostyushin.adaptertask.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс для представления данных о погоде.
 */
@AllArgsConstructor
@Getter
@Setter
public class WeatherData {
    /**
     * Состояние
     */
    private String condition;
    /**
     * Температура
     */
    private int temperature;
}