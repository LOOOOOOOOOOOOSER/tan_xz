package world.tan_xz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.tan_xz.entity.QueryLogs;
import world.tan_xz.service.QueryLogsService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "query-logs")
public class QueryLogsController  {

    @Resource
    private QueryLogsService queryLogsService;

    @GetMapping("/list")
    public List<QueryLogs> list() {
        return queryLogsService.findAll();
    }
}
