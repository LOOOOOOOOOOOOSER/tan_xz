package world.tan_xz.component;

/**
 * @author 谭轩钊
 * version 1.0
 */
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.util.HashMap;
import java.util.Map;

@Service
public class FastAPIClient {

    private final String fastApiUrl = "http://192.168.14.44:8000/query/"; // FastAPI 服务器地址

    public Map<String, Object> askQuestion(String query) {
        RestTemplate restTemplate = new RestTemplate();

        // 构建请求体
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("query", query);

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        try {
            // 发送 POST 请求
            ResponseEntity<Map> response = restTemplate.postForEntity(fastApiUrl, entity, Map.class);

            // 检查响应状态码
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                // 处理非200状态码
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("error", "API 请求失败，状态码: " + response.getStatusCode());
                return errorResponse;
            }
        } catch (Exception e) {
            // 捕获并处理异常
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "API 请求异常: " + e.getMessage());
            return errorResponse;
        }
    }
}


