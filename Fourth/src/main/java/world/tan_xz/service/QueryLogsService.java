package world.tan_xz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.tan_xz.dao.QueryLogsDao;
import world.tan_xz.entity.QueryLogs;
import world.tan_xz.utils.Assert;
import world.tan_xz.utils.BeanUtil;
import world.tan_xz.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Service
public class QueryLogsService {

    @Autowired
    protected QueryLogsDao queryLogsDao;

    public List<QueryLogs> findAll() {
        List<QueryLogs> logs = queryLogsDao.findAll();
        System.out.println("Retrieved logs: " + logs); // 或使用Logger
        return logs;
    }
    public QueryLogs save(QueryLogs log) {
        return queryLogsDao.save(log);
    }

}
