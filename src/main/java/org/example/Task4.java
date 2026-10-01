package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Task4 {
    public static void main(String[] args) throws Exception {
        // 1. Создаем HTTP-клиент
        HttpClient client = HttpClient.newHttpClient();

        // 2. Создаем GET-запрос
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/headers"))
                .GET()
                .build();

        // 3. Отправляем запрос и получаем ответ
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // 4. Парсим JSON с помощью Jackson
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.body());

        // 5. Извлекаем заголовки
        JsonNode headersNode = root.get("headers");

        // 6. Собираем значения заголовков в список
        List<String> headerValues = new ArrayList<>();
        Iterator<Map.Entry<String, JsonNode>> fields = headersNode.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            headerValues.add(entry.getValue().asText());
        }

        // 7. Выводим значения через запятую
        String result = String.join(", ", headerValues);
        System.out.println("Заголовки запроса: " + result);
    }
}