package world.tan_xz.config;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 谭轩钊
 * version 1.0
 */
@Configuration
public class CozeConfig {

    @Value("${coze.bot-id1}")
    private String botId;

    @Value("${coze.access-token}")
    private String token;

    @Bean
    public WebClient cozeWebClient() {
        return WebClient.builder()
                .baseUrl("https://api.coze.cn/open_api/v2/chat")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();
    }
    @Service
    public class AIService {
        private final WebClient webClient;
        private final String botId;

        public AIService(WebClient cozeWebClient, @Value("${coze.bot-id1}") String botId) {
            this.webClient = cozeWebClient;
            this.botId = botId;
        }

        public Mono<String> chat(String message) {
            Map<String, Object> body = new HashMap<>();
            body.put("bot_id", botId);
            body.put("user", "spring_client");
            body.put("query", message);

            return webClient.post()
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .map(node -> node.get("messages").get(0).get("content").asText());
        }
        public Flux<String> streamChat(String message) {
            Map<String, Object> bodyParams = new HashMap<>();
            bodyParams.put("bot_id", botId);
            bodyParams.put("stream", true);
            bodyParams.put("query", message);

            return webClient.post()
                    .bodyValue(bodyParams)
                    .accept(MediaType.TEXT_EVENT_STREAM)
                    .retrieve()
                    .bodyToFlux(String.class)
                    .filter(data -> data.startsWith("data:"))
                    .map(data -> data.substring(5).trim());
        }
    }


}

