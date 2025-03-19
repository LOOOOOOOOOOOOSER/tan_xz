package world.tan_xz.controller;

/**
 * @author 谭轩钊
 * version 1.0
 */
import org.springframework.web.bind.annotation.*;
import world.tan_xz.component.FastAPIClient;

import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final FastAPIClient fastAPIClient;

    public ChatController(FastAPIClient fastAPIClient) {
        this.fastAPIClient = fastAPIClient;
    }

    @PostMapping("/ask")
    public Map<String, Object> ask(@RequestBody Map<String, String> request) {
        String query = request.get("query");
        return fastAPIClient.askQuestion(query);
    }
}

