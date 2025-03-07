package world.tan_xz.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import world.tan_xz.dao.*;
import world.tan_xz.entity.HotIssue;
import world.tan_xz.utils.Assert;
import world.tan_xz.utils.BeanUtil;
import world.tan_xz.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class BaseService<T> implements IService<T> {

    @Autowired
    protected UserDao userDao;

    @Autowired
    protected HistoryDao historyDao;

    @Autowired
    protected IllnessDao illnessDao;

    @Autowired
    protected IllnessKindDao illnessKindDao;

    @Autowired
    protected IllnessMedicineDao illnessMedicineDao;

    @Autowired
    protected MedicineDao medicineDao;

    @Autowired
    protected MedicalNewsDao medicalNewsDao;

    @Autowired
    protected PageviewDao pageviewDao;

@Autowired
    protected CommentDao commentDao;

@Autowired
    protected FavoriteDao favoriteDao;

@Autowired
    protected HotIssueDao hotIssueDao;


}