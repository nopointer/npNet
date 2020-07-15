package npNet.nopointer.callback;

import com.google.gson.JsonSyntaxException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import npNet.nopointer.core.NpCall;
import npNet.nopointer.core.error.NpHttpError;
import npNet.nopointer.log.NpNetLog;
import retrofit2.HttpException;

public abstract class NpBaseCallback<T> implements NpCallback<T> {


    @Override
    public void onCompleted(boolean isSuccess, NpCall<T> call, NpHttpError npHttpError) {
        if (npHttpError == null) return;
        Throwable throwable = npHttpError.getCause();
        if (throwable != null) {
            if (printStackTraceEnable()) {
                NpNetLog.log("onCompleted->printStackTrace");
                throwable.printStackTrace();
            }
            if (throwable instanceof JsonSyntaxException) {
                NpNetLog.log("Json解析异常！！！");
            } else if (throwable instanceof SocketTimeoutException) {
                NpNetLog.log("网络连接超时！！！，请检查手机网络状态或者服务器是否宕机");
            } else if (throwable instanceof UnknownHostException) {
                NpNetLog.log("当前设备网络连接异常！！！");
            } else if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                switch (httpException.code()) {
                    case 400:
                        NpNetLog.log("请求参数错误");
                        break;
                    case 401:
                        NpNetLog.log("身份未授权");
                        break;
                    case 403:
                        NpNetLog.log("禁止访问");
                        break;
                    case 404:
                        NpNetLog.log("地址未找到");
                        break;
                    default:
                        NpNetLog.log("服务异常");
                        break;
                }
            }
        } else {
            NpNetLog.log("onCompleted->" + npHttpError.getNetCode() + "//" + npHttpError.getApiReturnCode() + "//" + npHttpError.getMessage());
        }
    }


}
