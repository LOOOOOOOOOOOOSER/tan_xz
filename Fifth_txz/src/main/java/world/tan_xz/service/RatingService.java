package world.tan_xz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.tan_xz.dao.RatingDao;
import world.tan_xz.entity.Rating;
import world.tan_xz.utils.Assert;
import world.tan_xz.utils.BeanUtil;
import world.tan_xz.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class RatingService extends BaseService<Rating> {

    @Autowired
    protected RatingDao ratingDao;

    @Override
    public List<Rating> query(Rating o) {
        QueryWrapper<Rating> wrapper = new QueryWrapper<>();
        if (Assert.notEmpty(o)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return ratingDao.selectList(wrapper);
    }

    @Override
    public List<Rating> all() {
        return query(null);
    }

    @Override
    public Rating save(Rating o) {
        if (Assert.isEmpty(o.getId())) {
            ratingDao.insert(o);
        } else {
            ratingDao.updateById(o);
        }
        return ratingDao.selectById(o.getId());
    }

    @Override
    public Rating get(Serializable id) {
        return ratingDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return ratingDao.deleteById(id);
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

