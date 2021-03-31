package me.duelsol.springbootseed.framework.support;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

/**
 * @author 冯奕骅
 */
@Getter
public class PagedResult {

    @Schema(description = "数据总条数")
    private final int total;

    @Schema(description = "当前页码")
    private final int page;

    @Schema(description = "本页数据")
    private final List<?> list;

    public PagedResult(int total, int page, List<?> list) {
        this.total = total;
        this.page = page;
        this.list = list;
    }

}
