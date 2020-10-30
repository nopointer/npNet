package npNet.nopointer.callback;

import npNet.nopointer.core.NpCall;
import npNet.nopointer.core.error.NpHttpError;

public interface NpCallback<T> {


    /**
     * 是否打印异常信息
     *
     * @return
     */
    boolean printStackTraceEnable();

    /**
     * 开始请求
     *
     * @param call
     */
    void onStart(NpCall<T> call);

    /**
     * 请求成功
     *
     * @param call
     * @param response
     */
    void onSuccess(NpCall<T> call, T response);


    /**
     * 请求失败
     *
     * @param call
     * @param npHttpError
     */
    void onFailure(NpCall<T> call, NpHttpError npHttpError);


    /**
     * 请求完成，不管是成功 还是失败都会回调到这里
     *
     * @param call
     * @param npHttpError
     */
    void onCompleted(NpCall<T> call, NpHttpError npHttpError);


}
