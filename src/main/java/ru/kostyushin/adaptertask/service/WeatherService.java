package ru.kostyushin.adaptertask.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.kostyushin.adaptertask.utils.Coordinates;
import ru.kostyushin.adaptertask.utils.WeatherData;
import ru.kostyushin.adaptertask.message.MessageA;
import ru.kostyushin.adaptertask.message.MessageB;

import java.util.Map;

/**
 * Сервис для получения данных о погоде и преобразования сообщений.
 */
@Service
public class WeatherService {
    /**
     * URL API сервиса погоды.
     */
    @Value("${weather.api.url}")
    private String weatherApiUrl;

    /**
     * Получает данные о погоде для указанных координат.
     * @param coordinates координаты для запроса данных о погоде
     * @return данные о погоде
     */
    public WeatherData getWeather(Coordinates coordinates) {
        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(weatherApiUrl)
                .queryParam("lat", coordinates.getLatitude())
                .queryParam("lon", coordinates.getLongitude());

        ResponseEntity<Map> response = restTemplate.getForEntity(builder.toUriString(), Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null) {
                String condition = responseBody.get("condition").toString();
                int temperature = (int) responseBody.get("temperature");
                return new WeatherData(condition, temperature);
            }
        }

        return new WeatherData("Unknown", 0);
    }

    /**
     * Преобразует объект сообщения типа MessageA в MessageB.
     * @param messageA объект сообщения типа MessageA
     * @return объект сообщения типа MessageB.
     */
    public MessageB convertToMessageB(MessageA messageA) {
        MessageB messageB = new MessageB();
        BeanUtils.copyProperties(messageA, messageB);
        return messageB;
    }
}