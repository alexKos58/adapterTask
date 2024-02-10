package ru.kostyushin.adaptertask.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Формат сообщений принимаемых сервисом «В»
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageB {
    /**
     * Текст сообщения
     */
    private String txt;
    /**
     * Дата создания сообщения
     */
    private LocalDateTime createdDt;
    /**
     * Текущая температура
     */
    private int currentTemp;
}