package demo.nopointer.npNet.net;

import android.text.TextUtils;
import android.util.Log;


import java.io.IOException;
import java.util.Set;

import npNet.nopointer.log.NpNetLog;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TokenHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = "69446a5e44316797c0d20f501dcc16c1";
        Request originalRequest = chain.request();
        if (TextUtils.isEmpty(token)) {
            return chain.proceed(originalRequest);
        } else {
//            FormBody formBody = (FormBody) originalRequest.body();
            FormBody.Builder newFormBuilder = new FormBody.Builder();
            if (originalRequest.body() instanceof FormBody) {
//                for (int i = 0; i < formBody.size(); i++) {
////                    newFormBuilder.addEncoded(formBody.name(i), formBody.encodedValue(i));
//                }
                FormBody formBody = newFormBuilder.add("token", token).build();

                Request updateRequest = originalRequest.newBuilder().put(formBody).build();
                NpNetLog.log("Token:" + updateRequest.toString());
                return chain.proceed(updateRequest);
            }
            return chain.proceed(originalRequest);
        }


//        Request request = chain.request();
//
//        Headers.Builder builder = request
//                .headers()
//                .newBuilder();
//
//        //统一追加Header参数
//        Headers newBuilder = builder.add("token", token)
//                .build();
//
//        Request newRequest = request.newBuilder()
//                .headers(newBuilder)
//                .build();
//
//        NpNetLog.log("Token:" + newRequest.toString());

//        return chain.proceed(newRequest);
//        6c616638289440da7dcd32ed19d9bc05
    }
}