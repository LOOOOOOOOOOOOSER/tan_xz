package world.tan_xz.component;

/**
 * @author 谭轩钊
 * version 1.0
 */
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CozeClient {
    private static final String API_URL = "https://api.coze.cn/open_api/v2/chat";

    public static String sendRequest(String botId, String token, String query) {
        try {
            // 1. 创建连接
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            // 2. 设置请求头
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestProperty("Content-Type", "application/json");

            // 3. 构建请求体
            // 3. 构建请求体
            String jsonInput = String.format(
                    "{ " +
                            "\"bot_id\": \"%s\"," +
                            "\"user\": \"java_client_001\"," +
                            "\"query\": \"%s\"," +
                            "\"stream\": false" +
                            "}",
                    botId,
                    query.replace("\"", "\\\"")  // 处理特殊字符转义
            );


            // 4. 发送请求
            conn.setDoOutput(true);
            try(OutputStream os = conn.getOutputStream()) {
                os.write(jsonInput.getBytes());
                os.flush();
            }

            // 5. 解析响应
            if (conn.getResponseCode() == 200) {
                StringBuilder response = new StringBuilder();
                try(BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                }
                return response.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
