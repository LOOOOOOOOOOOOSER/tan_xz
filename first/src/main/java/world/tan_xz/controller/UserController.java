package world.tan_xz.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.tan_xz.dto.RespResult;
import world.tan_xz.entity.User;
import world.tan_xz.utils.Assert;


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
}
