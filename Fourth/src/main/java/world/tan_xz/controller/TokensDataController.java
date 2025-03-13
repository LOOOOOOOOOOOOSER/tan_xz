package world.tan_xz.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import world.tan_xz.entity.TokensData;
import world.tan_xz.service.TokensDataService;
import world.tan_xz.dto.RespResult;
import java.util.List;

/**
 * Tokens数据控制层
 * @author 谭轩钊
 * version 1.0
 */
@Controller
@RequestMapping("/tokensData")
public class TokensDataController extends BaseController<TokensData> {

    @Autowired
    private TokensDataService tokensDataService;
    @Autowired
    public void setService(TokensDataService tokensDataService) {
        this.service = tokensDataService;
    }
    /**
     * 查询所有Tokens数据
     *
     * @return Tokens数据列表
     */
    @ResponseBody
    @GetMapping("/all")
    public RespResult all() {
        List<TokensData> list = service.all();
        return RespResult.success("查询成功", list);
    }
    /**
     * 根据条件查询Tokens数据
     *
     * @param obj 查询条件
     * @return 符合条件的Tokens数据列表
     */
    @ResponseBody
    @PostMapping("/query")
    public RespResult query(@RequestBody TokensData obj) {
        List<TokensData> list = service.query(obj);
        return RespResult.success("查询成功", list);
    }
}