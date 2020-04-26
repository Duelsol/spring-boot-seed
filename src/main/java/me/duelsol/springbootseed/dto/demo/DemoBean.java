package me.duelsol.springbootseed.dto.demo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 冯奕骅
 */
@Getter
@Setter
public class DemoBean {

    @Schema(description = "数量。")
    private Integer amount;

    @Schema(description = "明细。")
    private String detail;

    @Schema(description = "删除标识。")
    private Integer deleteFlag;

}
