package world.tan_xz.controller;

/**
 * @author 谭轩钊
 * version 1.0
 */
import org.springframework.web.bind.annotation.*;
import world.tan_xz.component.ApiClient;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiFileController {

    private final ApiClient apiClient;

    public ApiFileController(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    // ✅ 1. 生成 PPT
    @PostMapping("/generatePPT")
    public String generatePPT(@RequestBody Map<String, String> request) {
        try {
            String inputText = request.get("inputText");
            String pptPath = apiClient.generatePPT(inputText);
            return "PPT 生成成功: " + pptPath + "，请点击下载PPT将PPT下载到本地吧~";
        } catch (Exception e) {
            return "PPT 生成失败: " + e.getMessage();
        }
    }

    // ✅ 2. 生成视频
    @PostMapping("/generateVideo")
    public String generateVideo(@RequestBody Map<String, String> request) {
        try {
            String pptName = request.get("pptName");
            String videoPath = apiClient.generateVideo(pptName);
            return "视频生成成功: " + videoPath + "，请点击下载视频将视频下载到本地吧~";
        } catch (Exception e) {
            return "视频生成失败: " + e.getMessage();
        }
    }

    // ✅ 3. 下载文件
    // ✅ 下载 PPT
    @GetMapping("/downloadPPT")
    public String downloadPPT() throws IOException {
        String fileUrl = "http://192.168.14.44:8001/download_ppt";
        String fileName = "presentation.pptx";
        apiClient.downloadFile(fileUrl, fileName);
        return "✅ PPT 已成功下载到: " + fileName;
    }

    // ✅ 下载视频
    @GetMapping("/downloadVideo")
    public String downloadVideo() throws IOException {
        String fileUrl = "http://192.168.14.44:8001/download_video";
        String fileName = "output_video1.mp4";
        apiClient.downloadFile(fileUrl, fileName);
        return "✅ 视频已成功下载到: " + fileName;
    }

    // ✅ 4. 测试页面
    @GetMapping("/test")
    public String testPage() {
        return "<html>" +
                "<head>" +
                "<title>测试页面</title>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; margin: 20px; }" +
                "h1 { color: #333; }" +
                "form { margin-bottom: 20px; }" +
                "label { display: block; margin-bottom: 5px; }" +
                "input[type='text'] { width: 300px; padding: 5px; }" +
                "input[type='submit'] { padding: 5px 10px; background-color: #28a745; color: white; border: none; cursor: pointer; }" +
                "a { display: inline-block; margin-top: 10px; padding: 5px 10px; background-color: #007bff; color: white; text-decoration: none; }" +
                "a:hover { background-color: #0056b3; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<h1>测试页面</h1>" +
                "<form action='/api/generatePPT' method='post'>" +
                "   <label for='inputText'>输入PPT文本:</label>" +
                "   <input type='text' id='inputText' name='inputText'><br><br>" +
                "   <input type='submit' value='生成PPT'>" +
                "</form>" +
                "<form action='/api/generateVideo' method='post'>" +
                "   <label for='pptName'>请输入PPT名字:</label>" +
                "   <input type='text' id='pptName' name='pptName'><br><br>" +
                "   <input type='submit' value='生成视频'>" +
                "</form>" +
                "<a href='/api/downloadPPT'>下载PPT</a><br>" +
                "<a href='/api/downloadVideo'>下载视频</a>" +
                "</body>" +
                "</html>";
    }
}