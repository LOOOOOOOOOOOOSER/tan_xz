package world.tan_xz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import world.tan_xz.entity.HotNews;
import world.tan_xz.service.HotNewsService;

import java.util.List;
import java.util.Map;

/**
 * @author 谭轩钊
 * version 1.0
 */
@RestController
@RequestMapping("/hotNews")
public class HotNewsController {

    @Autowired
    private HotNewsService hotNewsService;

    @GetMapping
    public List<HotNews> getAllHotNews() {
        return hotNewsService.getAllHotNews();
    }


    @GetMapping("/{id}")
    public HotNews getHotNewsById(@PathVariable Integer id) {
        return hotNewsService.getHotNewsById(id);
    }

    @PostMapping
    public HotNews saveHotNews(@RequestBody HotNews hotNews) {
        return hotNewsService.saveHotNews(hotNews);
    }

    @DeleteMapping("/{id}")
    public void deleteHotNews(@PathVariable Integer id) {
        hotNewsService.deleteHotNews(id);
    }
}
