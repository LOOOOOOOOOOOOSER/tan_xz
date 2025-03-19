package world.tan_xz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import world.tan_xz.entity.SystemPerformance;
import world.tan_xz.service.SystemPerformanceService;
import world.tan_xz.dto.RespResult;


import java.util.List;

/**
 * 系统性能控制层
 * @author 谭轩钊
 * version 1.0
 */
@Controller
@RequestMapping("/systemPerformance")
public class SystemPerformanceController extends BaseController<SystemPerformance> {

    @Autowired
    private SystemPerformanceService systemPerformanceService;


    @Autowired
    public void setService(SystemPerformanceService systemPerformanceService) {
        this.service = systemPerformanceService;
    }

    /**
     * 查询所有系统性能数据
     *
     * @return 系统性能列表
     */
    @ResponseBody
    @GetMapping("/all")
    public RespResult all() {
        List<SystemPerformance> list = service.all();
        return RespResult.success("查询成功", list);
    }

    /**
     * 根据条件查询系统性能数据
     *
     * @param obj 查询条件
     * @return 符合条件的系统性能列表
     */
    @ResponseBody
    @PostMapping("/query")
    public RespResult query(@RequestBody SystemPerformance obj) {
        List<SystemPerformance> list = service.query(obj);
        return RespResult.success("查询成功", list);
    }
}