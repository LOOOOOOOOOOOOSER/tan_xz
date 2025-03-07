package world.tan_xz.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SchoolNewsController {

    @PostMapping("/api/school_news")
    public Map<String, String> handleSchoolNewsMessage(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        // 处理消息并生成回复
        String responseMessage = "这里是校园新闻的回复: " + message;
        String timestamp = "现在";

        Map<String, String> response = new HashMap<>();
        response.put("message", responseMessage);
        response.put("timestamp", timestamp);

        return response;
    }
}