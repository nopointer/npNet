package demo.nopointer.npNet.net.Resp;

import npNet.nopointer.core.NpBaseResp;

public class YCResp extends NpBaseResp {


    public YCResp() {
    }

    public YCResp(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    /**
     * 错误码
     */
    private int errorCode;
    /**
     * 展示信息
     */
    private String message;


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "YCResp{" +
                "errorCode=" + errorCode +
                ", message='" + message + '\'' +
                '}';
    }
}
