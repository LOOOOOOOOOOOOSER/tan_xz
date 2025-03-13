package world.tan_xz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.tan_xz.dao.SystemPerformanceDao;
import world.tan_xz.entity.SystemPerformance;
import world.tan_xz.utils.Assert;
import world.tan_xz.utils.BeanUtil;
import world.tan_xz.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 系统性能服务层
 * @author 谭轩钊
 * version 1.0
 */
@Service
public class SystemPerformanceService extends BaseService<SystemPerformance> {

    @Autowired
    protected SystemPerformanceDao dao;

    @Override
    public List<SystemPerformance> query(SystemPerformance o) {
        QueryWrapper<SystemPerformance> wrapper = new QueryWrapper<>();
        if (Assert.notEmpty(o)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return dao.selectList(wrapper);
    }

    @Override
    public List<SystemPerformance> all() {
        return query(null);
    }

    @Override
    public SystemPerformance save(SystemPerformance o) {
        if (Assert.isEmpty(o.getId())) {
            dao.insert(o);
        } else {
            dao.updateById(o);
        }
        return dao.selectById(o.getId());
    }

    @Override
    public SystemPerformance get(Serializable id) {
        return dao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return dao.deleteById(id);
    }
}