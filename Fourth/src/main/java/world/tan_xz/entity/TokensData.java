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
@TableName("tokens_data")
public class TokensData {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * Tokens总消耗
     */
    private Long totalTokensConsumed;

    /**
     * 平均Tokens消耗/问答
     */
    private Integer avgTokensPerQA;

    /**
     * Tokens消耗趋势
     */
    private String tokensConsumptionTrend;

    /**
     * 高峰Tokens消耗时段
     */
    private String peakTokensPeriod;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}