package world.tan_xz.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import world.tan_xz.component.CozeClient;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AcademicSupportController {

    @Value("${coze.bot-id1}")
    private String botId;

    @Value("${coze.access-token}")
    private String token;


    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/api/academic_support")
    public Map<String, String> handleAcademicSupportMessage(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        Map<String, String> response = new HashMap<>();
        String timestamp = "现在";

        // 调用Coze API
        String apiResponse = CozeClient.sendRequest(botId, token, message);

        try {
            if (apiResponse != null) {
                JsonNode rootNode = objectMapper.readTree(apiResponse);
                JsonNode messagesNode = rootNode.path("messages");

                for (JsonNode msgNode : messagesNode) {
                    if ("answer".equals(msgNode.path("type").asText())) {
                        response.put("message", msgNode.path("content").asText());
                        response.put("timestamp", timestamp);
                        return response;
                    }
                }
                response.put("message", "无法获取有效回复");
            } else {
                response.put("message", "无法获取Coze API的响应");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", "解析API响应失败");
        }
        response.put("timestamp", timestamp);
        return response;
    }
}
