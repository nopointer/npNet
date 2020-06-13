package npNet.nopointer.core;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

import npNet.nopointer.callback.NpCallback;
import npNet.nopointer.core.error.NpHttpError;
import npNet.nopointer.utils.log.LogUtil;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class NpBaseCall<T> implements NpCall<T> {

    private final Executor callbackExecutor;
    private final retrofit2.Call<T> delegate;

    public NpBaseCall(Executor callbackExecutor, retrofit2.Call<T> delegate) {
        this.callbackExecutor = callbackExecutor;
        this.delegate = delegate;
    }


    @Override
    public T execute() throws Throwable {
        Response<T> response = delegate.execute();
        T body = response.body();
        if (body != null) {
            return body;
        }
        throw new HttpException(response);
    }

    @Override
    public void enqueue(final NpCallback<T> callback) {
        callbackExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onStart(NpBaseCall.this);
                }
            }
        });
        delegate.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response != null && response.code() == 200 && response.body() != null) {
                    callSuccess(response.body());
                } else {
                    callFailure(new NpHttpError(new HttpException(response)));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (t instanceof NpHttpError) {
                    callFailure((NpHttpError) t);
                } else {
                    callFailure(new NpHttpError(t));
                }
            }

            private void callSuccess(@NonNull final T body) {
                callbackExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
//                        T transformer = callback.transform(RealCall.this, body);
                        //noinspection ConstantConditions
//                        Utils.checkNotNull(transformer == null, "transformer==null");
                        callback.onSuccess(NpBaseCall.this, body);
                        callback.onCompleted(true, NpBaseCall.this, null);
                    }
                });
            }

            private void callFailure(@NonNull final NpHttpError error) {
                callbackExecutor.execute(new Runnable() {
                    @Override
                    public void run() {

//                        HttpError error = callback.parseThrowable(RealCall.this, t);
                        //noinspection ConstantConditions
//                        Utils.checkNotNull(error == null, "error==null");
                        callback.onCompleted(false, NpBaseCall.this, error);
                    }
                });
            }

        });
    }

    @Override
    public boolean isExecuted() {
        return delegate.isExecuted();
    }

    @Override
    public void cancel() {
        delegate.cancel();
    }

    @Override
    public boolean isCanceled() {
        return delegate.isCanceled();
    }

    @Override
    public NpCall<T> clone() {
        return new NpBaseCall<>(callbackExecutor, delegate.clone());
    }


    @Override
    public Request request() {
        return delegate.request();
    }
}
