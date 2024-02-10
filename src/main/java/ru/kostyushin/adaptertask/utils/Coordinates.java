package ru.kostyushin.adaptertask.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс, предоставляющий координаты широты и долготы.
 */
@AllArgsConstructor
@Getter
@Setter
public class Coordinates {
    /**
     * Широта.
     */
    private double latitude;
    /**
     * Долгота.
     */
    private double longitude;
}