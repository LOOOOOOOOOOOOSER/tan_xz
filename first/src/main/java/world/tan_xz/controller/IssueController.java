package world.tan_xz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import world.tan_xz.entity.HotIssue;
import world.tan_xz.service.HotIssueServiceimpl;
import org.springframework.ui.Model;
/**
 * @author 谭轩钊
 * version 1.0
 */

@Controller
public class IssueController {

    @Autowired
    private HotIssueServiceimpl hotIssueService;

    @GetMapping("/issue/{id}")
    public String getIssueDetail(@PathVariable Integer id, Model model) {

        HotIssue issue = hotIssueService.get(id);
        if (issue == null) {
            return "error/404"; // 如果找不到问题，返回404页面
        }
        model.addAttribute("issue", issue);
        return "common/issue_detail";
    }
}
