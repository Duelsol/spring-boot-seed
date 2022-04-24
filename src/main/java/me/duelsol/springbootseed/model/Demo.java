package me.duelsol.springbootseed.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 冯奕骅
 */
@Setter
@Getter
@TableName("springmvc_demo")
public class Demo extends BaseModel<Demo> {

    private Integer amount;

    private String detail;

    @TableLogic(value = "0", delval = "1")
    @TableField(fill = FieldFill.INSERT)
    private Integer deleteFlag;

}
