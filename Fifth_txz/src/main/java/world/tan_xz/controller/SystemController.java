package world.tan_xz.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import world.tan_xz.entity.*;
import world.tan_xz.service.*;
import world.tan_xz.utils.Assert;
import world.tan_xz.utils.DateUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Controller
public class SystemController extends BaseController<User> {
    @Autowired
    private ClubService clubService;


    private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private QueryLogsService queryLogsService;

    @Autowired
    private InteractionHistoryService interactionHistoryService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private SmartAgentService smartAgentService;

    @GetMapping("/community")
    public String getClubDetail(@RequestParam Integer id, Model model) {
        Club club = clubService.getClubById(id);
        if (club==null)
        {
            return "error/404";
        }
        model.addAttribute("club", club);
        return "common/community";
    }
    /**
     * 首页
     */
    @GetMapping("/index.html")
    public String index(Map<String, Object> map) {
        return "index";
    }

    @GetMapping("/profile")
    public String profile(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        map.put("cur", "profile");  // 添加当前模块标识
        return "profile";
    }

    @GetMapping("/ppt-generator")
    public String ppt_generator(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        map.put("cur", "ppt-generator");  // 添加当前模块标识
        return "common/ppt-generator";
    }


    @GetMapping("/doctor")
    public String doctor(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        map.put("cur", "doctor");  // 添加当前模块标识
        return "doctor";
    }

    /**
     * 用户浏览量
     * @param id
     * @return
     */
    @PostMapping("/updateViewCount")
    @ResponseBody
    public String updateViewCount(@RequestParam Integer id) {
        hotNewsService.incrementViewCount(id);
        return "success";
    }

    /**
     * 解决跨域问题CORS
     * @param url
     * @return
     * @throws IOException
     */
    @GetMapping("/proxy")
    public ResponseEntity<byte[]> proxyImage(@RequestParam String url) throws IOException {
        URL imageUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
        connection.setRequestMethod("GET");
        InputStream inputStream = connection.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytesRead);
        }
        buffer.flush();
        byte[] imageBytes = buffer.toByteArray();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }
    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/index.html";
    }

    @GetMapping("/smart.html")
    public String smart(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        map.put("cur", "smart");  // 添加当前模块标识
        return "common/smart";
    }

    @GetMapping("hot-issues.html")
    public String hot(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        map.put("cur", "hot-issues");  // 添加当前模块标识
        return "common/hot-issues";
    }

    @GetMapping("/feedback.html")
    public String feedback(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        map.put("cur", "feedback");  // 添加当前模块标识
        return "feedback.html";
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

    /**
     * 数据报表
     * @param map
     * @return
     */
    @GetMapping("/data_report")
    public String data_report(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        List<QueryLogs> queryLogsList = queryLogsService.findAll();

        // 计算总Token消耗
        int totalTokens = queryLogsList.stream()
                .mapToInt(QueryLogs::getTokensUsed)
                .sum();
        // 计算消耗金额
        double cost = calculateCost(totalTokens);
        // 计算平均Token消耗/问答
        double avgTokens = queryLogsList.stream()
                .mapToInt(QueryLogs::getTokensUsed)
                .average()
                .orElse(0);

        // 计算高峰时段分布
        Map<String, Long> peakTimeMap = queryLogsList.stream()
                .collect(Collectors.groupingBy(
                        log -> {
                            LocalDateTime timestamp;
                            try {
                                timestamp = LocalDateTime.parse(log.getTimestamp(),
                                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                            } catch (Exception e) {
                                // 如果解析失败，可以记录日志或跳过该条数据
                                return "未知";
                            }
                            return timestamp.getHour() + ":00";
                        },
                        Collectors.counting()
                ));

        // 将Map转换为List并排序
        List<Map.Entry<String, Long>> peakTimeList = new ArrayList<>(peakTimeMap.entrySet());
        peakTimeList.sort(Map.Entry.comparingByKey());

        // 提取排序后的标签和数据
        List<String> peakTimeLabels = peakTimeList.stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        List<Long> peakTimeData = peakTimeList.stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        map.put("peakTimeLabels", peakTimeLabels);
        map.put("peakTimeData", peakTimeData);

        // 计算提问时间分布
        Map<String, Long> dailyCountMap = queryLogsList.stream()
                .collect(Collectors.groupingBy(
                        log -> {
                            String dayOfWeek = DateUtils.parseDayOfWeek(log.getTimestamp());
                            return dayOfWeek;
                        },
                        Collectors.counting()
                ));

        // 确保日期按照周一到周日的顺序排列
        List<String> orderedDays = Arrays.asList("周一", "周二", "周三", "周四", "周五", "周六", "周日");
        List<String> dailyLabels = new ArrayList<>();
        List<Long> dailyData = new ArrayList<>();
        for (String day : orderedDays) {
            dailyLabels.add(day);
            dailyData.add(dailyCountMap.getOrDefault(day, 0L));
        }

        map.put("dailyLabels", dailyLabels);
        map.put("dailyData", dailyData);

        // 计算系统响应时间趋势（按天分组）
        Map<String, Double> responseTimeMap = queryLogsList.stream()
                .collect(Collectors.groupingBy(
                        log -> {
                            String day = DateUtils.parseDay(log.getTimestamp());
                            return day;
                        },
                        Collectors.averagingDouble(QueryLogs::getResponseTimeMs)
                ));
        List<String> responseTimeLabels = new ArrayList<>(responseTimeMap.keySet());
        List<Double> responseTimeData = new ArrayList<>(responseTimeMap.values());

        map.put("totalTokens", totalTokens);
        map.put("avgTokens", avgTokens);
        map.put("peakTimeLabels", peakTimeLabels);
        map.put("peakTimeData", peakTimeData);
        map.put("responseTimeLabels", responseTimeLabels);
        map.put("responseTimeData", responseTimeData);
        map.put("cost",cost);
        return "common/data_report";
    }

    // 复用TokensDataController中的calculateCost方法
    private double calculateCost(long totalTokens) {
        // 比例为39373token消耗0.27人民币
        return (totalTokens * 0.27) / 39373;
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
     *
     * @param map
     * @return
     * 首页页面
     */

    @GetMapping("/home")
    public String home(Map<String, Object> map) {
//        if (Assert.isEmpty(loginUser)) {
//            return "redirect:/index.html";
//        }
        List<HotNews> hotNewsList = hotNewsService.getAllHotNews();
        map.put("hotNewsList", hotNewsList);

        List<HotIssue> hotIssuesList = hotIssueServiceimpl.getTop10HotIssuesByCommentCount();
        map.put("hotIssuesList", hotIssuesList);
        map.put("cur", "home");  // 添加当前模块标识
        return "common/home";
    }

    /**
     * 用户首页的新闻详情
     * @param map
     * @param id
     * @return
     */
    @GetMapping("/homeHotNewsDetail")
    public String homeHotNewsDetail(Map<String, Object> map, @RequestParam Integer id) {
//        if (Assert.isEmpty(loginUser)) {
//            return "redirect:/index.html";
//        }
        HotNews hotNews = hotNewsService.getHotNewsById(id);
        map.put("hotNews", hotNews);
        return "common/home_hotNewsDetail";
    }
    /**
     * 查看新闻详情
     */
    @GetMapping("/findHotNewsOne")
    public String findHotNewsOne(Map<String, Object> map, @RequestParam Integer id) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        HotNews hotNews = hotNewsService.getHotNewsById(id);
        map.put("hotNews", hotNews);
        return "common/hotNewsDetail";
    }
    /**
     * 删除新闻
     */
    @GetMapping("/deleteHotNews")
    public String deleteHotNews(@RequestParam Integer id) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        hotNewsService.deleteHotNews(id);
        return "redirect:/all_hotNews";
    }

    /**
     * 添加新闻页面
     */
    @GetMapping("add_hotNews")
    public String add_HotNews(Map<String, Object> map, @RequestParam(required = false) Integer id) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        HotNews hotNews = new HotNews();
        if (id != null) {
            hotNews = hotNewsService.getHotNewsById(id);
        }
        map.put("hotNews", hotNews);
        // 添加当前登录用户到页面
        map.put("loginUser", loginUser);
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
        // 设置当前登录用户的user_id
        hotNews.setUserId(loginUser.getId());
        hotNewsService.saveHotNews(hotNews);
        return "redirect:/all_hotNews";
    }


    /**
     * 所有反馈
     */
    @GetMapping("/all-feedback")
    public String feedback2(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        List<Feedback> feedbackList = feedbackService.all();

        map.put("feedbackList", feedbackList);
        return "all-feedback";
    }

    /**
     * 获取智能体的平均评分
     */
    @GetMapping("/getAgentAverageRating")
    @ResponseBody
    public Map<String, Object> getAgentAverageRating(@RequestParam Integer agentId) {
        double averageRating = ratingService.getAgentAverageRating(agentId);
        Map<String, Object> result = new HashMap<>();
        result.put("averageRating", averageRating > 0 ? String.format("%.1f", averageRating) : "暂无评分");
        return result;
    }

    /**
     * 提交智能体的评论与评分
     */
    /**
     * 提交智能体的评论与评分
     */
    @PostMapping("/submitRating")
    @ResponseBody
    public String submitRating(@RequestParam Double score, @RequestParam String comment, @RequestParam Integer agentId) {
        if (Assert.isEmpty(loginUser)) {
            return "error:用户未登录";
        }

        // 创建评分对象
        Rating rating = new Rating();
        rating.setUserId(loginUser.getId());
        rating.setScore(score);
        rating.setComment(comment);
        rating.setRatingTime(new Date());
        rating.setAgentId(agentId);

        // 保存评分
        try {
            ratingService.save(rating);
            return "success";
        } catch (Exception e) {
            logger.error("提交评分失败", e);
            return "error:提交失败，请稍后重试";
        }
    }
    /**
     * 获取用户的交互历史记录
     */
    @GetMapping("/getInteractionHistory")
    @ResponseBody
    public List<InteractionHistory> getInteractionHistory() {
        if (Assert.isEmpty(loginUser)) {
            return Collections.emptyList();
        }

        // 查询用户的交互历史记录
        InteractionHistory query = new InteractionHistory();
        query.setUserId(loginUser.getId());
        List<InteractionHistory> historyList = interactionHistoryService.query(query);

        // 确保返回的列表中包含所有必要字段
        historyList.forEach(history -> {
            history.setUserMessage(history.getUserMessage());
            history.setAgentResponse(history.getAgentResponse());
            history.setInteractionTime(history.getInteractionTime());
        });

        return historyList;
    }

    /**
     * 获取智能体的详细信息
     */
    @GetMapping("/getSmartAgentDetails")
    @ResponseBody
    public SmartAgent getSmartAgentDetails(@RequestParam Integer agentId) {
        return smartAgentService.get(agentId);
    }

    /**
     * 自动跳转到首页
     * @return
     */
    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }
}

