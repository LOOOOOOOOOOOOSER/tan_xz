package world.tan_xz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.tan_xz.dao.HotNewsDao;
import world.tan_xz.entity.HotNews;
import world.tan_xz.utils.Assert;

import java.io.Serializable;
import java.util.List;

/**
 * @author 谭轩钊
 * version 1.0
 */
@Service
public class HotNewsService {

    @Autowired
    private HotNewsDao hotNewsDao;

    public List<HotNews> getAllHotNews() {
        return hotNewsDao.selectList(null);
    }

    public HotNews getHotNewsById(Serializable id) {
        return hotNewsDao.selectById(id);
    }

    public HotNews saveHotNews(HotNews hotNews) {
        if (Assert.isEmpty(hotNews.getId())) {
            hotNewsDao.insert(hotNews);
        } else {
            hotNewsDao.updateById(hotNews);
        }
        return hotNewsDao.selectById(hotNews.getId());
    }

    public void incrementViewCount(Integer id) {
        HotNews hotNews = hotNewsDao.selectById(id);
        if (hotNews != null) {
            hotNews.setViewCount(hotNews.getViewCount() + 1);
            hotNewsDao.updateById(hotNews);
        }
    }
    public void deleteHotNews(Serializable id) {
        hotNewsDao.deleteById(id);
    }
}
