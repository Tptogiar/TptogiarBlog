package dto;

public enum ResultCode {

    // 成功标识
    SUCCEED("200"),
    // 失败标识
    FAIL("400");

    public String code;
    ResultCode(String  code) {
        this.code = code;
    }
}
