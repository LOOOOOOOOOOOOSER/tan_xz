package world.tan_xz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.tan_xz.dao.CommentDao;
import world.tan_xz.dao.HotIssueDao;
import world.tan_xz.entity.Comment;
import world.tan_xz.entity.Favorite;
import world.tan_xz.entity.HotIssue;
import world.tan_xz.entity.User;
import world.tan_xz.utils.Assert;
import world.tan_xz.utils.BeanUtil;
import world.tan_xz.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class HotIssueServiceimpl extends BaseService<HotIssue> {

    @Autowired
    protected HotIssueDao hotIssueDao;


    @Override
    public List<HotIssue> query(HotIssue o) {
        QueryWrapper<HotIssue> wrapper = new QueryWrapper<>();
        if (Assert.notEmpty(o)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return hotIssueDao.selectList(wrapper);
    }

    @Override
    public List<HotIssue> all() {
        List<HotIssue> hotIssues = hotIssueDao.selectList(new QueryWrapper<HotIssue>());
        for (HotIssue hotIssue : hotIssues) {
            User user = userDao.selectById(hotIssue.getUserId());
            if (user != null) {
                hotIssue.setUserName(user.getUserName()); // 设置发布者姓名
                hotIssue.setUserImg(user.getImgPath()); // 设置发布者头像

            }

        }
        return hotIssues;
    }


    @Override
    public HotIssue save(HotIssue o) {
        if (Assert.isEmpty(o.getId())) {
            hotIssueDao.insert(o);
        } else {
            hotIssueDao.updateById(o);
        }
        return hotIssueDao.selectById(o.getId());
    }

    @Override
    public HotIssue get(Serializable id) {
        return hotIssueDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return hotIssueDao.deleteById(id);
    }


    public List<Comment> getCommentsByIssueId(Integer issueId) {
        return commentDao.selectList(new QueryWrapper<Comment>().eq("issue_id", issueId));
    }


    public List<Favorite> getFavoritesByUserId(Integer userId) {
        return favoriteDao.selectList(new QueryWrapper<Favorite>().eq("user_id", userId));
    }

    public List<HotIssue> getAllHotIssues() {
        return hotIssueDao.selectList(new QueryWrapper<HotIssue>());
    }
}