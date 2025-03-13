package world.tan_xz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.tan_xz.dao.QuestionAnswerRecordDao;
import world.tan_xz.entity.QuestionAnswerRecord;
import world.tan_xz.utils.Assert;
import world.tan_xz.utils.BeanUtil;
import world.tan_xz.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 问答记录服务层
 * @author 谭轩钊
 * version 1.0
 */
@Service
public class QuestionAnswerRecordService extends BaseService<QuestionAnswerRecord> {

    @Autowired
    protected QuestionAnswerRecordDao dao;

    @Override
    public List<QuestionAnswerRecord> query(QuestionAnswerRecord o) {
        QueryWrapper<QuestionAnswerRecord> wrapper = new QueryWrapper<>();
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
    public List<QuestionAnswerRecord> all() {
        return query(null);
    }

    @Override
    public QuestionAnswerRecord save(QuestionAnswerRecord o) {
        if (Assert.isEmpty(o.getId())) {
            dao.insert(o);
        } else {
            dao.updateById(o);
        }
        return dao.selectById(o.getId());
    }

    @Override
    public QuestionAnswerRecord get(Serializable id) {
        return dao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return dao.deleteById(id);
    }
}