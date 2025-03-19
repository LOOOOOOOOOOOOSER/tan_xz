package world.tan_xz.service;

/**
 * @author 谭轩钊
 * version 1.0
 */
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.json.JSONObject;

public class ChatService {
    public static String askQuestion(String question) {
        String url = "http://192.168.14.44:8000/query/";  // 阿里云 FastAPI 地址
        RestTemplate restTemplate = new RestTemplate();
        // 创建 JSON 请求
        JSONObject requestJson = new JSONObject();
        requestJson.put("query", question);
        // 设置 HTTP 头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson.toString(), headers);
        // 发送 POST 请求
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return responseEntity.getBody();
    }
}