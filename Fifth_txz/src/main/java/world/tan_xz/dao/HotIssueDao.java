package world.tan_xz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import world.tan_xz.entity.HotIssue;

/**
 * @author 谭轩钊
 * version 1.0
 */
@Repository
public interface HotIssueDao extends BaseMapper<HotIssue> {
    HotIssue selectById(Long id);
}

