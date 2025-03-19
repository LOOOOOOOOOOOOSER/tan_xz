package world.tan_xz.service;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.tan_xz.dao.HistoryDao;
import world.tan_xz.entity.History;
import world.tan_xz.utils.Assert;
import world.tan_xz.utils.BeanUtil;
import world.tan_xz.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class HistoryService extends BaseService<History> {

    @Autowired
    protected HistoryDao historyDao;

    @Override
    public List<History> query(History o) {
        QueryWrapper<History> wrapper = new QueryWrapper();
        if (Assert.notEmpty(o)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return historyDao.selectList(wrapper);
    }

    @Override
    public List<History> all() {
        return query(null);
    }

    @Override
    public History save(History o) {
        if (Assert.isEmpty(o.getId())) {
            historyDao.insert(o);
        } else {
            historyDao.updateById(o);
        }
        return historyDao.selectById(o.getId());
    }

    @Override
    public History get(Serializable id) {
        return historyDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return historyDao.deleteById(id);
    }

    public boolean insetOne(Integer uid, Integer type, String nameValue) {
        History history = new History();
        history.setUserId(uid).setKeyword(nameValue).setOperateType(type);
        return historyDao.insert(history) > 0;
    }

    public List<Map<String, Object>> findList(Integer userId) {
        List<History> list = historyDao.selectList(new QueryWrapper<History>().eq("user_id", userId)
                .orderByDesc("create_time"));
        List<History> histories = list.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(History::getKeyword))), LinkedList::new));
        histories.sort((h1, h2) -> -h1.getCreateTime().compareTo(h2.getCreateTime()));
        List<History> historyList = histories.stream().limit(10).collect(Collectors.toList());
        System.out.println(histories.size());
        List<Map<String, Object>> mapList = new LinkedList<>();
        historyList.forEach(his -> {
            Map<String, Object> map = cn.hutool.core.bean.BeanUtil.beanToMap(his);
            Integer operateType = MapUtil.getInt(map, "operateType");
            if (operateType == 1) {
                List<String> keyword = Arrays.asList((MapUtil.getStr(map, "keyword")).split(","));


            }
            mapList.add(map);
        });
        return mapList;
    }
}