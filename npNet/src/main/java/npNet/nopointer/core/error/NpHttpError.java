package npNet.nopointer.core.error;

/**
 * 错误码工具
 */
public class NpHttpError extends RuntimeException {

    /**
     * 网络服务器状态码,-1是没有赋值的netCode，不具备参考价值
     */
    private int netCode = -1;
    /**
     * 接口数据返回的状态码，-1是没有赋值的apiReturnCode，不具备参考价值
     */
    private int apiReturnCode = -1;

    /**
     * 接口数据返回的数据，由于是继承至异常，所以没办法使用泛型来做参数解析，只能返回原始json数据
     */
    private String apiReturnData = null;

    /**
     * 接口返回的信息
     */
    private String apiReturnMsgOrMessage = null;


    public NpHttpError(Throwable cause) {
        super(cause);
    }

    public NpHttpError(int netCode, int apiReturnCode, String msg) {
        this.netCode = netCode;
        this.apiReturnCode = apiReturnCode;
        this.apiReturnMsgOrMessage = msg;
    }

    public NpHttpError(int netCode, int apiReturnCode, String msg, String data) {
        this.netCode = netCode;
        this.apiReturnCode = apiReturnCode;
        this.apiReturnData = data;
        this.apiReturnMsgOrMessage = msg;
    }


    public int getNetCode() {
        return netCode;
    }


    public void setNetCode(int netCode) {
        this.netCode = netCode;
    }

    public int getApiReturnCode() {
        return apiReturnCode;
    }

    public String getApiReturnData() {
        return apiReturnData;
    }

    @Override
    public String toString() {

        String appMessage = "NpHttpError{" +
                "netCode=" + netCode +
                ", apiReturnCode=" + apiReturnCode +
                ", apiReturnData='" + apiReturnData + '\'' +
                ", apiReturnMsgOrMessage='" + apiReturnMsgOrMessage + '\'' +
                '}';
        String exceptionMesage = "";
        if (getCause() != null) {
            exceptionMesage = getCause().toString();
        }
        return exceptionMesage + " &&& " + appMessage;
    }
}
