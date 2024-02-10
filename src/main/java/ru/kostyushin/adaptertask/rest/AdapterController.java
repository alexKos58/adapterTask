package ru.kostyushin.adaptertask.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.kostyushin.adaptertask.message.MessageA;
import ru.kostyushin.adaptertask.message.MessageB;
import ru.kostyushin.adaptertask.message.MessageResponse;
import ru.kostyushin.adaptertask.service.WeatherService;

/**
 * Контроллер для обработки входящих сообщений и их передачи в другие сервисы.
 */
@RestController
public class AdapterController {
    /**
     * URL-адрес ServiceB.
     */
    @Value("${serviceB.url}")
    private String serviceBUrl;

    /**
     * Сервис для получения данных о погоде и преобразования сообщений.
     */
    private final WeatherService weatherService;

    public AdapterController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * Обрабатывает входящее сообщение от ServiceA и отправляет его в ServiceB.
     * @param messageA - входящее сообщение от ServiceA
     * @return ответ о результате обработки сообщения
     */
    @PostMapping("/processMessage")
    public ResponseEntity<MessageResponse> processMessage(@RequestBody MessageA messageA) {
        if (messageA == null || messageA.getMsg() == null || messageA.getMsg().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Empty message from Service A", null));
        }

        if (!"ru".equals(messageA.getLng())) {
            return ResponseEntity
                    .ok(new MessageResponse("Message language is not Russian, ignoring.", null));
        }

        messageA.setWeather(weatherService.getWeather(messageA.getCoordinates()));
        MessageB messageB = weatherService.convertToMessageB(messageA);
        ResponseEntity<String> responseEntity = sendToServiceB(messageB);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(new MessageResponse("Message processed successfully", messageA));
        } else {
            return ResponseEntity.status(responseEntity.getStatusCode())
                    .body(new MessageResponse("Error sending message to Service B", null));
        }
    }

    /**
     * Отправляет сообщение в ServiceB.
     * @param messageB - сообщение для отправки в ServiceB
     * @return ответ от ServiceB
     */
    private ResponseEntity<String> sendToServiceB(MessageB messageB) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(serviceBUrl, messageB, String.class);
    }
}
