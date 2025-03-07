package world.tan_xz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.tan_xz.Mapper.CommentMapper;
import world.tan_xz.dao.CommentDao;
import world.tan_xz.dao.HotIssueDao;
import world.tan_xz.entity.Comment;
import world.tan_xz.entity.HotIssue;
import world.tan_xz.entity.User;
import world.tan_xz.utils.Assert;
import world.tan_xz.utils.BeanUtil;
import world.tan_xz.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceimpl extends BaseService<Comment> {

    @Autowired
    protected CommentDao commentDao;

    @Autowired
    private HotIssueDao hotIssueDao;
    @Override
    public List<Comment> query(Comment o) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        if (Assert.notEmpty(o)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return commentDao.selectList(wrapper);
    }



    @Override
    public List<Comment> all() {
        List<Comment> comments = commentDao.selectList(new QueryWrapper<Comment>());
        for (Comment comment : comments) {
            User user = userDao.selectById(comment.getUserId());
            if (user != null) {
                comment.setUserName(user.getUserName()); // 设置发布者姓名
                comment.setUserImg(user.getImgPath()); // 设置发布者头像
            }
        }
        return comments;
    }
    @Override
    public Comment save(Comment o) {
        if (Assert.isEmpty(o.getId())) {
            commentDao.insert(o);
        } else {
            commentDao.updateById(o);
        }
        return commentDao.selectById(o.getId());
    }

    @Override
    public Comment get(Serializable id) {
        return commentDao.selectById(id);
    }
    public List<Comment> getCommentsByIssueId(Integer issueId) {
        List<Comment> comments = commentDao.selectList(new QueryWrapper<Comment>().eq("issue_id", issueId));
        for (Comment comment : comments) {
            User user = userDao.selectById(comment.getUserId());
            if (user != null) {
                comment.setUserName(user.getUserName()); // 设置发布者姓名
                comment.setUserImg(user.getImgPath()); // 设置发布者头像
            }
        }
        return comments;
    }

    public List<Comment> getCommentByUserId(Integer userId) {
        List<Comment> comments = commentDao.selectList(new QueryWrapper<Comment>().eq("user_id", userId));
        for (Comment comment : comments) {
            HotIssue issue = hotIssueDao.selectById( comment.getIssueId()); // 使用 HotIssueDao 获取帖子信息
            if (issue != null) {
                comment.setHotIssue(issue); // 设置帖子信息
            } else {
                // 提供默认值以避免前端解析错误
                HotIssue defaultIssue = new HotIssue();
                defaultIssue.setContent("未知内容");
                comment.setHotIssue(defaultIssue);
            }
        }
        return comments;
    }
    @Override
    public int delete(Serializable id) {
        return commentDao.deleteById(id);
    }
}