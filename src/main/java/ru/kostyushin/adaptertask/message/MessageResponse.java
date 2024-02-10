package ru.kostyushin.adaptertask.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс представляет собой ответ на обработку сообщения.
 */
@AllArgsConstructor
@Getter
@Setter
public class MessageResponse {
    /**
     * Сообщение о результате обработки.
     */
    private String message;
    /**
     * Обработанное сообщение.
     */
    private MessageA processedMessage;
}