package world.tan_xz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import world.tan_xz.service.UserService;

/**
 * @author 谭轩钊
 * version 1.0
 */
@Controller
public class PermissionManagementController {
    private final UserService userService;

    public PermissionManagementController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 权限管理页面
     */
    @GetMapping("/permission-management")
    public String permissionManagement(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "common/permission-management";
    }
}
