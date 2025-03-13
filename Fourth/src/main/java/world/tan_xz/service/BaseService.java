package world.tan_xz.service;

import org.springframework.beans.factory.annotation.Autowired;
import world.tan_xz.dao.*;

public abstract class BaseService<T> implements IService<T> {

    @Autowired
    protected UserDao userDao;

    @Autowired
    protected HistoryDao historyDao;



    @Autowired
    protected PageviewDao pageviewDao;

@Autowired
    protected CommentDao commentDao;

@Autowired
    protected FavoriteDao favoriteDao;

@Autowired
    protected HotIssueDao hotIssueDao;

@Autowired
    protected QuestionAnswerRecordDao questionAnswerRecordDao;

@Autowired
    protected SystemPerformanceDao systemPerformanceDao;

@Autowired
    protected TokensDataDao tokensDataDao;
@Autowired
    protected ClubDao clubDao;
@Autowired
    protected QueryLogsDao queryLogsDao;
}