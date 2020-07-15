package npNet.nopointer.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;

import npNet.nopointer.log.NpNetLog;
import npNet.nopointer.utils.NpUtils;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.SkipCallbackExecutor;

/**
 * NpCall 处理适配器
 */
public class NpCallAdapterFactory extends CallAdapter.Factory {

    private static final String RETURN_TYPE = Call.class.getSimpleName();

    private static final NpCallAdapterFactory ourInstance = new NpCallAdapterFactory();

    public static NpCallAdapterFactory getInstance() {
        return ourInstance;
    }

    private NpCallAdapterFactory() {
    }


    /**
     * Extract the raw class type from {@code type}. For example, the type representing
     * {@code List<? extends Runnable>} returns {@code List.class}.
     */
    public static Class<?> getRawType(Type type) {
        return CallAdapter.Factory.getRawType(type);
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(returnType) != NpCall.class) {
            NpNetLog.log("RETURN_TYPE" + "getRawType(returnType) != NpCall.class");
            return null;
        }
        if (!(returnType instanceof ParameterizedType)) {
            NpNetLog.log("RETURN_TYPE" + "!(returnType instanceof ParameterizedType)");
            throw new IllegalArgumentException(
                    String.format("%s return type must be parameterized as %s<Foo> or %s<? extends Foo>", RETURN_TYPE, RETURN_TYPE, RETURN_TYPE));
        }
        final Type responseType = getParameterUpperBound(0, (ParameterizedType) returnType);
        //支持SkipCallbackExecutor
        final Executor executor = NpUtils.isAnnotationPresent(annotations, SkipCallbackExecutor.class)
                ? null
                : retrofit.callbackExecutor();
        return new CallAdapter<Object, NpCall<?>>() {
            @Override
            public Type responseType() {
                return responseType;
            }

            @Override
            public NpCall<Object> adapt(retrofit2.Call<Object> call) {
                if (executor != null) {
                    return new NpBaseCall<>(executor, call);
                }
                return new NpBaseCall<>(executor, call);
            }
        };
    }
}
