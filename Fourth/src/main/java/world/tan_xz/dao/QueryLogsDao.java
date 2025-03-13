package world.tan_xz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import world.tan_xz.entity.QueryLogs;

/**
 * @author 谭轩钊
 * version 1.0
 */

public interface QueryLogsDao extends MongoRepository<QueryLogs,String> {
}
