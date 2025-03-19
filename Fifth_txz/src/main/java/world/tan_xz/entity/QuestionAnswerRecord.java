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
@TableName("question_answer_record")
public class QuestionAnswerRecord {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 总问答数
     */
    private Integer totalQA;

    /**
     * 平均问答轮数
     */
    private Integer avgTurnsPerSession;

    /**
     * 热门问题TOP10
     */
    private String hotQuestions;

    /**
     * 问答分类统计
     */
    private String qaByCategory;

    /**
     * 问答时间分布
     */
    private String qaByTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}