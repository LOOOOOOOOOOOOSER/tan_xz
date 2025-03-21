package world.tan_xz.service;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationOutput;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.aigc.generation.models.QwenParam;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.MessageManager;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.utils.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import world.tan_xz.utils.Getsend;

import java.net.URLEncoder;


@Service
public class ApiService {

    private Getsend getsend;

//    public String query(String queryMessage) {
//        try {
//            String sendRecvGet=getsend.sendGet(URLEncoder.encode(queryMessage, "UTF-8"),"http://localhost:5000/index/");
//            return sendRecvGet;
//        } catch (Exception e) {
//            return "智能医生现在不在线，请稍后再试～";
//        }
//    }
    @Value("${ai-key}")
    private String apiKey;

    public String query(String queryMessage) {
        Constants.apiKey = apiKey;
        try {
            Generation gen = new Generation();
            MessageManager msgManager = new MessageManager(10);
            Message systemMsg = Message.builder().role(Role.SYSTEM.getValue()).content("你是谭轩钊同学开发的智能助手").build();
            Message userMsg = Message.builder().role(Role.USER.getValue()).content(queryMessage).build();
            msgManager.add(systemMsg);
            msgManager.add(userMsg);
            QwenParam param = QwenParam.builder().model(Generation.Models.QWEN_TURBO).messages(msgManager.get()).resultFormat(QwenParam.ResultFormat.MESSAGE).build();
            GenerationResult result = gen.call(param);
            GenerationOutput output = result.getOutput();
            Message message = output.getChoices().get(0).getMessage();
            return message.getContent();
        } catch (Exception e) {
            return "智能助手现在不在线，请稍后再试～";
        }
    }
}
