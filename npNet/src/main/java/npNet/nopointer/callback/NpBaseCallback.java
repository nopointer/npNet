package npNet.nopointer.callback;

import com.google.gson.JsonSyntaxException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import npNet.nopointer.core.NpCall;
import npNet.nopointer.core.error.NpHttpError;
import npNet.nopointer.utils.log.LogUtil;
import retrofit2.HttpException;

public abstract class NpBaseCallback<T> implements NpCallback<T> {


    @Override
    public void onCompleted(boolean isSuccess, NpCall<T> call, NpHttpError npHttpError) {
        if (npHttpError == null) return;
        Throwable throwable = npHttpError.getCause();
        if (throwable != null) {
            if (printStackTraceEnable()) {
                LogUtil.e("onCompleted->printStackTrace");
                throwable.printStackTrace();
            }
            if (throwable instanceof JsonSyntaxException) {
                LogUtil.e("Json解析异常！！！");
            } else if (throwable instanceof SocketTimeoutException) {
                LogUtil.e("网络连接超时！！！，请检查手机网络状态或者服务器是否宕机");
            } else if (throwable instanceof UnknownHostException) {
                LogUtil.e("当前设备网络连接异常！！！");
            } else if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                switch (httpException.code()) {
                    case 400:
                        LogUtil.e("请求参数错误");
                        break;
                    case 401:
                        LogUtil.e("身份未授权");
                        break;
                    case 403:
                        LogUtil.e("禁止访问");
                        break;
                    case 404:
                        LogUtil.e("地址未找到");
                        break;
                    default:
                        LogUtil.e("服务异常");
                        break;
                }
            }
        } else {
            LogUtil.e("onCompleted->" + npHttpError.getNetCode() + "//" + npHttpError.getApiReturnCode() + "//" + npHttpError.getMessage());
        }
    }


}
