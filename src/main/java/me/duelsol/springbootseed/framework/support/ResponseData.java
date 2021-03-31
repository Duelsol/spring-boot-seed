package me.duelsol.springbootseed.framework.support;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * @author 冯奕骅
 */
@Getter
public class ResponseData {

    @Schema(description = "编号，非\"0\"时都表示发生错误。")
    private final String code;

    @Schema(description = "消息，在错误时说明具体原因。")
    private final String message;

    public ResponseData() {
        this.code = "0";
        this.message = null;
    }

    public ResponseData(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
