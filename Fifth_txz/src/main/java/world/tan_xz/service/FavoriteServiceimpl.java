package world.tan_xz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.tan_xz.dao.FavoriteDao;
import world.tan_xz.entity.Favorite;
import world.tan_xz.entity.HotIssue;
import world.tan_xz.utils.Assert;
import world.tan_xz.utils.BeanUtil;
import world.tan_xz.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class FavoriteServiceimpl extends BaseService<Favorite> {

    @Autowired
    protected FavoriteDao favoriteDao;

    @Autowired
    protected HotIssueServiceimpl hotIssueService;
    @Override
    public List<Favorite> query(Favorite o) {
        QueryWrapper<Favorite> wrapper = new QueryWrapper<>();
        if (Assert.notEmpty(o)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return favoriteDao.selectList(wrapper);
    }

    @Override
    public List<Favorite> all() {
        return query(null);
    }

    @Override
    public Favorite save(Favorite o) {
        if (Assert.isEmpty(o.getId())) {
            // 检查是否已存在相同的收藏记录
            QueryWrapper<Favorite> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", o.getUserId())
                   .eq("issue_id", o.getIssueId());
            Favorite existingFavorite = favoriteDao.selectOne(wrapper);
            if (existingFavorite != null) {
                return existingFavorite; // 如果已存在，直接返回
            }
            favoriteDao.insert(o);
        } else {
            favoriteDao.updateById(o);
        }
        return favoriteDao.selectById(o.getId());
    }

    @Override
    public Favorite get(Serializable id) {
        return favoriteDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return favoriteDao.deleteById(id);
    }

    public void removeByIssueIdAndUserId(Integer issueId, Integer userId) {
        QueryWrapper<Favorite> wrapper = new QueryWrapper<>();
        wrapper.eq("issue_id", issueId)
               .eq("user_id", userId);
        favoriteDao.delete(wrapper);
    }

    public Favorite addFavorite(Favorite favorite) {
        favoriteDao.insert(favorite);
        return favoriteDao.selectById(favorite.getId());
    }

    public List<Favorite> getFavoritesByUserId(Integer userId) {
        List<Favorite> favorites = favoriteDao.selectList(new QueryWrapper<Favorite>().eq("user_id", userId));
        for (Favorite favorite : favorites) {
            HotIssue issue = hotIssueService.getIssueById(favorite.getIssueId());
            if (issue != null) {
                favorite.setHotIssue(issue); // 设置对应的帖子信息
            } else {
                // 提供默认值以避免前端解析错误
                favorite.setHotIssue(new HotIssue());
            }
        }
        return favorites;
    }
}