package world.tan_xz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 谭轩钊
 * version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("rating")
public class Rating {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer interactionId;

    private Double score;

    private String comment;

    private Date ratingTime;

    private Integer agentId;
}
