package ru.kostyushin.adaptertask.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.kostyushin.adaptertask.utils.Coordinates;
import ru.kostyushin.adaptertask.utils.WeatherData;

/**
 * Формат сообщений, отправляемых «Service А»
 */
@AllArgsConstructor
@Getter
@Setter
public class MessageA {
    /**
     * Текст сообщения
     */
    private String msg;
    /**
     * Язык
     */
    private String lng;
    /**
     * Координаты
     */
    private Coordinates coordinates;
    /**
     * Сведения о погоде
     */
    private WeatherData weather;
}
