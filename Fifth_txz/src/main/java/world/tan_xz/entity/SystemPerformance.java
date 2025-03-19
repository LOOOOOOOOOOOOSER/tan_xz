package world.tan_xz.entity;

/**
 * @author 谭轩钊
 * version 1.0
 */
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("system_performance")
public class SystemPerformance {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 系统响应时间
     */
    private Double avgResponseTime;

    /**
     * 系统错误率
     */
    private Double errorRate;

    /**
     * 知识库更新频率
     */
    private String knowledgeBaseUpdateFrequency;

    /**
     * 用户反馈数量
     */
    private Integer userFeedbackCount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}