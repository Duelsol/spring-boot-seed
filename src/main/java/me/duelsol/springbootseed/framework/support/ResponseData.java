package me.duelsol.springbootseed.framework.support;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * @author 冯奕骅
 */
@Getter
public class ResponseData {

    @Schema(description = "编号，非\"00000\"时都表示发生错误。")
    private final String code;

    @Schema(description = "消息，在错误时说明具体原因。")
    private final String message;

    @Schema(description = "数据。")
    private final Object data;

    public ResponseData(String code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public ResponseData(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public static ResponseData success(String message, Object data) {
        return new ResponseData(ResponseCode.SUCCESS.getCode(), message, data);
    }

    public static ResponseData success(String message) {
        return success(message, null);
    }

    public static ResponseData success(Object data) {
        return success(null, data);
    }

    public static ResponseData of(ResponseCode code) {
        return of(code, null);
    }

    public static ResponseData of(ResponseCode code, Object data) {
        return code.toData(data);
    }

}
