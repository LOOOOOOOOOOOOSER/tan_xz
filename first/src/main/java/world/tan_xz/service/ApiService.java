package world.tan_xz.service;

import org.springframework.stereotype.Service;
import world.tan_xz.utils.Getsend;

import java.net.URLEncoder;


@Service
public class ApiService {

    private Getsend getsend;

    public String query(String queryMessage) {
        try {
            String sendRecvGet=getsend.sendGet(URLEncoder.encode(queryMessage, "UTF-8"),"http://localhost:5000/index/");
            return sendRecvGet;
        } catch (Exception e) {
            return "智能医生现在不在线，请稍后再试～";
        }
    }
}
