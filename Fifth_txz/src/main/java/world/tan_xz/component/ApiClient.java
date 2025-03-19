package world.tan_xz.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.core.io.InputStreamResource;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
public class ApiClient {

    @Value("http://192.168.14.44:8001")
    private String fastApiUrl;

    @Value("E:/test_PPT")
    private String downloadFolder;

    private final RestTemplate restTemplate = new RestTemplate();

    // ✅ 1. 生成 PPT
    public String generatePPT(String inputText) {
        String url = fastApiUrl + "/generate_ppt/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{\"input_text\": \"" + inputText + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return (String) response.getBody().get("ppt_path");
        } else {
            throw new RuntimeException("PPT 生成失败！");
        }
    }

    // ✅ 2. 生成视频
    public String generateVideo(String pptName) {
        if(!pptName.endsWith(".pptx"))
        {
            pptName += ".pptx";
        }
        String url = fastApiUrl + "/generate_video/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{\"ppt_name\": \"" + pptName + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return (String) response.getBody().get("video_path");
        } else {
            throw new RuntimeException("视频生成失败！");
        }
    }

    // ✅ 3. 下载文件（PPT 或 视频）
    public void downloadFile(String fileUrl, String fileName) throws IOException {
        URL url = new URL(fileUrl);
        Path targetPath = Paths.get(downloadFolder, fileName);
        Files.copy(url.openStream(), targetPath);
        System.out.println("✅ 文件下载成功：" + targetPath);
    }
}