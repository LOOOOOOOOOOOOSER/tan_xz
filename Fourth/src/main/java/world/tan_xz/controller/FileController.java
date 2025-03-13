package world.tan_xz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import world.tan_xz.component.OssClient;
import world.tan_xz.dto.RespResult;
import world.tan_xz.entity.User;
import world.tan_xz.utils.Assert;

import java.io.IOException;

/**
 * 文件控制器
 *
 * @author XUEW
 */
@RestController
@RequestMapping("/file")
public class FileController extends BaseController<User> {

    @Autowired
    private OssClient ossClient;

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public RespResult upload(@RequestParam("file") MultipartFile file) throws IOException {
        String url = ossClient.upload(file, String.valueOf(loginUser.getId()));
        if (Assert.isEmpty(url)) {
            System.out.println(url);;
            return RespResult.fail("上传失败", url);
        }
        return RespResult.success("上传成功", url);
    }
}
