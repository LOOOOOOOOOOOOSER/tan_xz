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
@TableName("hot_news")
public class HotNews {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id，关联user表
     */
    private Integer userId;

    /**
     * 作者
     */
    private String author;

    /**
     * 资料来源
     */
    private String source;

    /**
     * 浏览次数
     */
    private Integer viewCount;

    /**
     * 新闻图片路径
     */
    private String imgPath;

    /**
     * 新闻标题
     */
    private String title;

    /**
     * 新闻内容
     */
    private String context;

    /**
     * 创建时间
     */
    private Date createTime;

    private Date updateTime;
}
