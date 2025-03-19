package world.tan_xz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 谭轩钊
 * version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("interaction_history")
public class InteractionHistory {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer agentId;

    private String userMessage;

    private String agentResponse;

    private Data interactionTime;
}
