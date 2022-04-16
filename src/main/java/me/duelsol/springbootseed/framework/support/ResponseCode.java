package me.duelsol.springbootseed.framework.support;

/**
 * @author 冯奕骅
 */
public enum ResponseCode {

    /**
     * 成功
     */
    SUCCESS("00000", "成功");

    private final String code;
    private final String description;

    ResponseCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public ResponseData toData(Object data) {
        return new ResponseData(this.getCode(), this.getDescription(), data);
    }

    public ResponseData toData() {
        return this.toData(null);
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
