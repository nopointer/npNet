package demo.nopointer.npNet.net.impl;


import npNet.nopointer.callback.NpBaseCallback;
import npNet.nopointer.core.NpCall;
import npNet.nopointer.core.error.NpHttpError;
import npNet.nopointer.log.NpNetLog;

public abstract class YCNetCallback<T> extends NpBaseCallback<T> {

    @Override
    public boolean printStackTraceEnable() {
        return false;
    }

    @Override
    public void onStart(NpCall<T> call) {
        NpNetLog.log("onStart->" + call.request().toString());
    }


    @Override
    public void onFailure(NpCall<T> call, NpHttpError npHttpError) {
        super.onFailure(call, npHttpError);
        NpNetLog.log("onFailure->npHttpError:"+npHttpError.toString());
    }

    @Override
    public void onCompleted(NpCall<T> call, NpHttpError npHttpError) {
        super.onCompleted(call, npHttpError);
    }
}
