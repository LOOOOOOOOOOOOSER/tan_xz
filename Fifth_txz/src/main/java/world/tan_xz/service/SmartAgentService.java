package world.tan_xz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.tan_xz.dao.RatingDao;
import world.tan_xz.dao.SmartAgentDao;
import world.tan_xz.entity.Rating;
import world.tan_xz.entity.SmartAgent;
import world.tan_xz.utils.Assert;
import world.tan_xz.utils.BeanUtil;
import world.tan_xz.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class SmartAgentService extends BaseService<SmartAgent> {

    @Autowired
    protected SmartAgentDao smartAgentDao;

    @Autowired
    protected RatingDao ratingDao;

    @Override
    public List<SmartAgent> query(SmartAgent o) {
        QueryWrapper<SmartAgent> wrapper = new QueryWrapper<>();
        if (Assert.notEmpty(o)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return smartAgentDao.selectList(wrapper);
    }

    @Override
    public List<SmartAgent> all() {
        return query(null);
    }

    @Override
    public SmartAgent save(SmartAgent o) {
        if (Assert.isEmpty(o.getId())) {
            smartAgentDao.insert(o);
        } else {
            smartAgentDao.updateById(o);
        }
        return smartAgentDao.selectById(o.getId());
    }

    @Override
    public SmartAgent get(Serializable id) {
        return smartAgentDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return smartAgentDao.deleteById(id);
    }

    public double getAgentAverageRating(Integer agentId) {
        QueryWrapper<Rating> wrapper = new QueryWrapper<>();
        wrapper.eq("agent_id", agentId);
        List<Rating> ratings = ratingDao.selectList(wrapper);
        if (ratings.isEmpty()) {
            return 0;
        }
        double totalScore = ratings.stream().mapToDouble(Rating::getScore).sum();
        return totalScore / ratings.size();
    }
}
