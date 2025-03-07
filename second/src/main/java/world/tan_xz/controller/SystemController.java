package world.tan_xz.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import world.tan_xz.constant.MedicalConstants;
import world.tan_xz.entity.*;
import world.tan_xz.utils.Assert;

import java.util.*;


@Controller
public class SystemController extends BaseController<User> {

    /**
     * 首页
     */
    @GetMapping("/index.html")
    public String index(Map<String, Object> map) {
        return "index";
    }

    /**
     * 智能医生
     */
    @GetMapping("/doctor")
    public String doctor(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        return "doctor";
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/index.html";
    }

    /**
     * 所有反馈
     */
    @GetMapping("/all-feedback")
    public String feedback(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        List<Feedback> feedbackList = feedbackService.all();

        map.put("feedbackList", feedbackList);
        return "all-feedback";
    }

    /**
     * 我的资料
     */
    @GetMapping("/profile")
    public String profile(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        return "profile";
    }

    /**
     *
     *我的收藏与评论
     */
    @GetMapping("/my_favorite_comments")
    public String my_favorite_comments(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        map.put("userId", loginUser.getId()); // 添加用户ID到页面
        return "common/my_favorite_comments";
    }

    @GetMapping("/question_management")
    public String question_management(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        map.put("userId", loginUser.getId()); // 添加用户ID到页面
        return "common/question_management";
    }
    /**
     * 热门话题
     * @return
     */
    @GetMapping("hot-issues.html")
    public String hot() {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        return "common/hot-issues";
    }

    /**
     * 智能体
     * @return
     */
    @GetMapping("/smart.html")
    public String smart() {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        return "common/smart";
    }

    /**
     * 新闻管理页面
     */
    @GetMapping("all_hotNews")
    public String allHotNews(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        List<HotNews> hotNewsList = hotNewsService.getAllHotNews();
        map.put("hotNews", hotNewsList);
        return "common/all_hotNews";
    }
    /**
     * 添加新闻页面
     */
    @GetMapping("add_hotNews")
    public String add_HotNews(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        List<HotNews> hotNewsList = hotNewsService.getAllHotNews();
        map.put("hotNews", hotNewsList);
        System.out.println("HotNews list: " + hotNewsList);
        return "common/add_hotNews";
    }


    /**
     * 保存新闻
     */
    @PostMapping("/save-hotNews")
    public String saveHotNews(@ModelAttribute HotNews hotNews) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        hotNewsService.saveHotNews(hotNews);
        return "redirect:/all_hotNews";
    }
    /**
     * 查询相关疾病
     */
    @GetMapping("findIllness")
    public String findIllness(Map<String, Object> map, Integer kind, String illnessName, Integer page) {
        // 处理page
        page = ObjectUtils.isEmpty(page) ? 1 : page;

        Map<String, Object> illness = illnessService.findIllness(kind, illnessName, page);
        if (Assert.notEmpty(kind)) {
            map.put("title", illnessKindService.get(kind).getName() + (illnessName == null ? "" : ('"' + illnessName + '"' + "的搜索结果")));
        } else {
            map.put("title", illnessName == null ? "全部" : ('"' + illnessName + '"' + "的搜索结果"));
        }
        if (loginUser != null && kind != null) {
            historyService.insetOne(loginUser.getId(), MedicalConstants.TYPE_OPERATE,
                    illnessKindService.get(kind).getId() + "," + (Assert.isEmpty(illnessName) ? "无" : illnessName));
        }
        if (loginUser != null && Assert.notEmpty(illnessName)) {
            historyService.insetOne(loginUser.getId(), MedicalConstants.TYPE_ILLNESS, illnessName);
        }
        map.putAll(illness);
        map.put("page", page);
        map.put("kind", kind);
        map.put("illnessName", illnessName);
        map.put("kindList", illnessKindService.findList());
        map.put("history", loginUser == null ? null : historyService.findList(loginUser.getId()));
        return "search-illness";
    }

    /**
     * 查询相关疾病下的药
     */
    @GetMapping("findIllnessOne")
    public String findIllnessOne(Map<String, Object> map, Integer id) {
        Map<String, Object> illnessOne = illnessService.findIllnessOne(id);
        Illness illness = illnessService.get(id);
        if (loginUser != null) {
            historyService.insetOne(loginUser.getId(), MedicalConstants.TYPE_ILLNESS, illness.getIllnessName());
        }
        map.putAll(illnessOne);
        return "illness-reviews";
    }

    /**
     * 查询相关疾病下的药
     */
    @GetMapping("findMedicineOne")
    public String findMedicineOne(Map<String, Object> map, Integer id) {
        Medicine medicine = medicineService.get(id);
//        historyService.insetOne(loginUser.getId(),MedicalConstants.TYPE_MEDICINE,medicine.getMedicineName());
        map.put("medicine", medicine);
        return "medicine";
    }

    /**
     * 查询相关疾病下的药
     */
    @GetMapping("findMedicines")
    public String findMedicines(Map<String, Object> map, String nameValue, Integer page) {
        // 处理page
        page = ObjectUtils.isEmpty(page) ? 1 : page;
        if (loginUser != null && Assert.notEmpty(nameValue)) {
            historyService.insetOne(loginUser.getId(), MedicalConstants.TYPE_MEDICINE, nameValue);
        }
        map.putAll(medicineService.getMedicineList(nameValue, page));
        map.put("history", loginUser == null ? null : historyService.findList(loginUser.getId()));
        map.put("title", nameValue);
        return "illness";
    }

    /**
     * 查询相关疾病下的药
     */
    @GetMapping("globalSelect")
    public String globalSelect(Map<String, Object> map, String nameValue) {
        nameValue = nameValue.replace("，", ",");
        List<String> idArr = Arrays.asList(nameValue.split(","));
        //首先根据关键字去查询
        Set<Illness> illnessSet = new HashSet<>();
        idArr.forEach(s -> {
            Illness one = illnessService.getOne(new QueryWrapper<Illness>().like("illness_name", s));
            if (ObjectUtil.isNotNull(one)) {
                illnessSet.add(one);
            }
        });
        idArr.forEach(s -> {
            Illness one = illnessService.getOne(new QueryWrapper<Illness>().like("special_symptom", s));
            if (ObjectUtil.isNotNull(one)) {
                illnessSet.add(one);
            }
        });
        idArr.forEach(s -> {
            Illness one = illnessService.getOne(new QueryWrapper<Illness>().like("illness_symptom", s));
            if (ObjectUtil.isNotNull(one)) {
                illnessSet.add(one);
            }
        });
        map.put("illnessSet", illnessSet);
        return "index";
    }

    /**
     * 添加疾病页面
     */
    @GetMapping("add-illness")
    public String addIllness(Integer id, Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        Illness illness = new Illness();
        if (Assert.notEmpty(id)) {
            illness = illnessService.get(id);
        }
        List<IllnessKind> illnessKinds = illnessKindService.all();
        map.put("illness", illness);
        map.put("kinds", illnessKinds);
        return "add-illness";
    }

    /**
     * 添加药品页面
     */
    @GetMapping("add-medical")
    public String addMedical(Integer id, Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        List<Illness> illnesses = illnessService.all();
        Medicine medicine = new Medicine();
        if (Assert.notEmpty(id)) {
            medicine = medicineService.get(id);
            for (Illness illness : illnesses) {
                List<IllnessMedicine> query = illnessMedicineService.query(IllnessMedicine.builder().medicineId(id).illnessId(illness.getId()).build());
                if (Assert.notEmpty(query)) {
                    illness.setIllnessMedicine(query.get(0));
                }
            }
        }
        map.put("illnesses", illnesses);
        map.put("medicine", medicine);
        return "add-medical";
    }

    /**
     * 疾病管理页面
     */
    @GetMapping("all-illness")
    public String allIllness(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        List<Illness> illnesses = illnessService.all();
        for (Illness illness : illnesses) {
            illness.setKind(illnessKindService.get(illness.getKindId()));
        }
        map.put("illnesses", illnesses);
        return "all-illness";
    }

    /**
     * 药品管理页面
     */
    @GetMapping("all-medical")
    public String allMedical(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        List<Medicine> medicines = medicineService.all();
        map.put("medicines", medicines);
        return "all-medical";
    }

}
