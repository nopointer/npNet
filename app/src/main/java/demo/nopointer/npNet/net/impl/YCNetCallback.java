package demo.nopointer.npNet.net.impl;


import npNet.nopointer.callback.NpBaseCallback;
import npNet.nopointer.core.NpCall;
import npNet.nopointer.core.error.NpHttpError;
import npNet.nopointer.utils.log.LogUtil;

public abstract class YCNetCallback<T> extends NpBaseCallback<T> {

    @Override
    public boolean printStackTraceEnable() {
        return true;
    }

    @Override
    public void onStart(NpCall<T> call) {
//        NpLog.e("onStart->" + call.request().toString());
    }


    @Override
    public void onCompleted(boolean isSuccess, NpCall<T> call, NpHttpError npHttpError) {
        super.onCompleted(isSuccess, call, npHttpError);
        if (npHttpError!=null){
//            LogUtil.e("onCompleted--->" + npHttpError.getApiReturnCode());
        }
    }
}
