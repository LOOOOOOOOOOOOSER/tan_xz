package world.tan_xz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.tan_xz.dao.FavoriteDao;
import world.tan_xz.entity.Favorite;
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
    public Favorite addFavorite(Favorite favorite) {
        favoriteDao.insert(favorite);
        return favoriteDao.selectById(favorite.getId());
    }

    public List<Favorite> getFavoritesByUserId(Integer userId) {
        return favoriteDao.selectList(new QueryWrapper<Favorite>().eq("user_id", userId));
    }
}