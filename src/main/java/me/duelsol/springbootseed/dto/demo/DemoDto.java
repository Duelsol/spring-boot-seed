package me.duelsol.springbootseed.dto.demo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 冯奕骅
 */
@Getter
@Setter
public class DemoDto {

    @Schema(description = "数量。")
    private Integer amount;

    @Schema(description = "明细。")
    private String detail;

    @Schema(description = "删除标识。")
    private Integer deleteFlag;

    private List<String> userIds = new ArrayList<>();

}
