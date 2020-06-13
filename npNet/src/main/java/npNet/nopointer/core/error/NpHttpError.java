package npNet.nopointer.core.error;

import npNet.nopointer.utils.log.LogUtil;

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

    public NpHttpError(Throwable cause) {
        super(cause);
    }




    public NpHttpError(int netCode, int apiReturnCode, String msg) {
        super(msg);
        this.netCode = netCode;
        this.apiReturnCode = apiReturnCode;
        LogUtil.e("NpHttpError==>" + toString());
    }


    public int getNetCode() {
        return netCode;
    }

    public int getApiReturnCode() {
        return apiReturnCode;
    }

    @Override
    public String toString() {
        return "NpHttpError{" +
                "netCode=" + netCode +
                ", apiReturnCode=" + apiReturnCode +
                '}';
    }
}
