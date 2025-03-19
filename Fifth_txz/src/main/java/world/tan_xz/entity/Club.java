package world.tan_xz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("clubs")
public class Club {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 社团名称
     */
    private String name;
    /**
     * 成立背景
     */
    private String background;
    /**
     * 活动内容
     */
    private String activities;
    /*
     * 特色亮点
     */
    private String highlight;
    /**
     * 图片路径
     */
    private String imagepath;
    /**
     * 类型 (1-4)
     */
    private Integer type;
}