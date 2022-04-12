package me.duelsol.springbootseed.framework.support;

/**
 * @author 冯奕骅
 */
public enum ErrorCode {

    SUCCESS("00000", "成功");

    private final String code;
    private final String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
