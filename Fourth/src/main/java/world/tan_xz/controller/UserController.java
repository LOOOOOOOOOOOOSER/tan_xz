package world.tan_xz.controller;

import org.springframework.web.bind.annotation.*;
import world.tan_xz.dto.RespResult;
import world.tan_xz.entity.User;
import world.tan_xz.utils.Assert;

import java.util.Map;


@RestController
@RequestMapping(value = "user")
public class UserController extends BaseController<User> {

    /**
     * 修改资料
     */
    @PostMapping("/saveProfile")
    public RespResult saveProfile(User user) {
        if (Assert.isEmpty(user)) {
            return RespResult.fail("保存对象不能为空");
        }
        user = userService.save(user);
        session.setAttribute("loginUser", user);
        return RespResult.success("保存成功");
    }

    /**
     * 修改密码
     */
    @PostMapping("/savePassword")
    public RespResult savePassword(String oldPass, String newPass) {
        if (!loginUser.getUserPwd().equals(oldPass)) {
            return RespResult.fail("旧密码错误");
        }
        loginUser.setUserPwd(newPass);
        loginUser = userService.save(loginUser);
        session.setAttribute("loginUser", loginUser);
        return RespResult.success("保存成功");
    }

    /**
     * 更新角色状态
     * @param payload
     * @return
     */
    @PostMapping("/update-role")
    public RespResult updateRole(@RequestBody Map<String, Object> payload) {
        Long userId = Long.valueOf(payload.get("userId").toString());
        Integer roleStatus = Integer.valueOf(payload.get("roleStatus").toString());
        userService.updateUserRole(userId, roleStatus);
        return RespResult.success("角色状态更新成功");
    }
}
