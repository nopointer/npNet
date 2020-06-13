package npNet.nopointer.core;

import android.support.annotation.NonNull;

import npNet.nopointer.callback.NpCallback;
import okhttp3.Request;
import retrofit2.Response;

public interface NpCall<T> extends Cloneable {

    /**
     * Synchronously send the request and return its response body.
     *
     * @throws DisposedException       if {@link LifeCall} has been dispose
     * @throws retrofit2.HttpException if {@link Response#body()} is null
     * @throws java.io.IOException     if a problem occurred talking to the server.
     * @throws RuntimeException        (and subclasses) if an unexpected error occurs creating the request
     *                                 or decoding the response.
     */
    @NonNull
    T execute() throws Throwable;

    /**
     * Asynchronously send the request and notify {@code callback} of its response or if an error
     * occurred talking to the server, creating the request, or processing the response.
     */
    void enqueue(NpCallback<T> callback);


    boolean isExecuted();

    /**
     * Cancel this call. An attempt will be made to cancel in-flight calls, and if the call has not
     * yet been executed it never will be.
     */
    void cancel();

    /**
     * True if {@link #cancel()} was called.
     */
    boolean isCanceled();

    /**
     * Create a new, identical call to this one which can be enqueued or executed even if this call
     * has already been.
     */
    NpCall<T> clone();

    /**
     * The original HTTP request.
     */
    Request request();
}
