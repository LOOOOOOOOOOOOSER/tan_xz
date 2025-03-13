package world.tan_xz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import world.tan_xz.entity.QuestionAnswerRecord;
import world.tan_xz.service.QuestionAnswerRecordService;
import world.tan_xz.dto.RespResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 问答记录控制层
 * @author 谭轩钊
 * version 1.0
 */
@Controller
@RequestMapping("/questionAnswerRecord")
public class QuestionAnswerRecordController extends BaseController<QuestionAnswerRecord> {

    @Autowired
    private QuestionAnswerRecordService questionAnswerRecordService;


    @Autowired
    public void setService(QuestionAnswerRecordService questionAnswerRecordService) {
        this.service = questionAnswerRecordService;
    }

    /**
     * 查询所有问答记录
     *
     * @return 问答记录列表
     */
    @ResponseBody
    @GetMapping("/all")
    public RespResult all() {
        List<QuestionAnswerRecord> list = service.all();
        return RespResult.success("查询成功", list);
    }

    /**
     * 根据条件查询问答记录
     *
     * @param obj 查询条件
     * @return 符合条件的问答记录列表
     */
    @ResponseBody
    @PostMapping("/query")
    public RespResult query(@RequestBody QuestionAnswerRecord obj) {
        List<QuestionAnswerRecord> list = service.query(obj);
        return RespResult.success("查询成功", list);
    }
}