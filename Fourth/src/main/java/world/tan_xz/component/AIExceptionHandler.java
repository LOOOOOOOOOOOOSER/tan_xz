package world.tan_xz.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;

import java.util.Map;

/**
 * @author 谭轩钊
 * version 1.0
 */
@ControllerAdvice
public class AIExceptionHandler {



    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<String> handleCozeError(WebClientResponseException ex) {
        HttpStatus status = ex.getStatusCode();
        switch (status) {
            case UNAUTHORIZED:
                return ResponseEntity.status(401).body("无效的访问令牌");
            case TOO_MANY_REQUESTS:
                return ResponseEntity.status(429).body("请求超限");
            default:
                return ResponseEntity.status(500).body("AI服务异常");
        }
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(500).body("服务器内部错误: " + ex.getMessage());
    }
}

