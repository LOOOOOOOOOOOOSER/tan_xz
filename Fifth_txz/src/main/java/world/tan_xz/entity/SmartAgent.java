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
@TableName("smart_agent")
public class SmartAgent {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String description;

    private Data createdTime;

    private Data updatedTime;
}
