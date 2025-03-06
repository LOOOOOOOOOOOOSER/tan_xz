package world.tan_xz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 谭轩钊
 * version 1.0
 */
@Controller
public class HotController {
    @GetMapping("hot-issues.html")
    public String hot() {
        return "common/hot-issues";
    }
}
