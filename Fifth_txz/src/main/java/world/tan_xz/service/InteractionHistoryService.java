package world.tan_xz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.tan_xz.dao.InteractionHistoryDao;
import world.tan_xz.entity.InteractionHistory;
import world.tan_xz.utils.Assert;
import world.tan_xz.utils.BeanUtil;
import world.tan_xz.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class InteractionHistoryService extends BaseService<InteractionHistory> {

    @Autowired
    protected InteractionHistoryDao interactionHistoryDao;

    @Override
    public List<InteractionHistory> query(InteractionHistory o) {
        QueryWrapper<InteractionHistory> wrapper = new QueryWrapper<>();
        if (Assert.notEmpty(o)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return interactionHistoryDao.selectList(wrapper);
    }

    @Override
    public List<InteractionHistory> all() {
        return query(null);
    }

    @Override
    public InteractionHistory save(InteractionHistory o) {
        if (Assert.isEmpty(o.getId())) {
            interactionHistoryDao.insert(o);
        } else {
            interactionHistoryDao.updateById(o);
        }
        return interactionHistoryDao.selectById(o.getId());
    }

    @Override
    public InteractionHistory get(Serializable id) {
        return interactionHistoryDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return interactionHistoryDao.deleteById(id);
    }
}