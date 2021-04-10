package demo.nopointer.npNet.net;


import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import demo.nopointer.npNet.net.Resp.YCResp;
import demo.nopointer.npNet.net.impl.YCNetCallback;
import demo.nopointer.npNet.net.parser.GsonConverterFactory;
import npNet.nopointer.core.NpCallAdapterFactory;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;

/**
 * 网络管理
 */
public class NetManager {
    private static final NetManager ourInstance = new NetManager();

    public static NetManager getInstance() {
        return ourInstance;
    }

    private Retrofit mRetrofit;

    public static final String replacePathMLB2Name = "replacePathMLB2";
    public static final String replacePathMLB2 = "http://mlb2.app168.com/index.php/Home";


    private OkHttpClient.Builder getClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(60, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(60, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(60, TimeUnit.SECONDS);
        httpClientBuilder.retryOnConnectionFailure(true);
        httpClientBuilder.addInterceptor(new TokenHeaderInterceptor());
        return httpClientBuilder;
    }

    private NetManager() {

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC);
        Gson gson = builder.create();

        OkHttpClient okHttpClient = getClient().build();

        ////步骤4:构建Retrofit实例
        mRetrofit = new Retrofit.Builder()
                //设置网络请求BaseUrl地址
//                .baseUrl("http://app.laisidilin.com/")
                .baseUrl("http://testhlq.app198.com/index.php/home/")
//                .baseUrl("http://sk.runchinaup.com/")
//                .baseUrl("https://wanandroid.com/")
                .callFactory(new ReplaceUrlCallFactory(okHttpClient) {
                    @Nullable
                    @Override
                    protected HttpUrl getNewUrl(String baseUrlName, Request request) {
//                        Log.e("请求的地址是:print", "baseUrlName:" + baseUrlName);
//                        NpNetLog.log("请求是:" + request.url());
                        if (baseUrlName.equals(replacePathMLB2Name)) {
                            String oldUrl = request.url().toString();
                            String newUrl = oldUrl.replace("http://testhlq.app198.com/index.php/home", replacePathMLB2);
//                            NpNetLog.log("替换后的地址:" + newUrl);
                            return HttpUrl.get(newUrl);
                        }
//                        if (baseUrlName.equals("baidu")) {
//                            String oldUrl = request.url().toString();
//                            String newUrl = oldUrl.replace("https://wanandroid.com/", "https://www.baidu.com/");
//                            return HttpUrl.get(newUrl);
//                        }
                        return null;
                    }
                })
                .addCallAdapterFactory(NpCallAdapterFactory.getInstance())
                //设置数据解析器
//                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
                .build();
    }


    public void login() {
//        ApiService apiService = mRetrofit.create(ApiService.class);
//        NpCall<LoginResult> call = apiService.login("13631697178", "9812", "86");
//        call.enqueue(new YCNetCallback<LoginResult>() {
//            @Override
//            public void onSuccess(NpCall<LoginResult> call, LoginResult response) {
//                NpNetLog.log("登录结果:" + new Gson().toJson(response));
//            }
//        });
//        new YCNetCallback<YCRespData>() {
//            @Override
//            public void onSuccess(NpCall<YCRespData> call, YCRespData response) {
//                NpLog.e("                                              ");
//                NpLog.e("==============onResponse======================");
//                NpLog.e("onResponse->call:" + call.request().toString());
//                try {
//                    NpLog.e("onResponse->response:" + new Gson().toJson(response));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        call.enqueue(new YCNetCallback<YCRespData<UserEntity>>() {
//            @Override
//            public void onSuccess(NpCall<YCRespData<UserEntity>> call, YCRespData<UserEntity> response) {
//                NpLog.e("                                              ");
//                NpLog.e("==============onResponse======================");
//                NpLog.e("onResponse->call:" + call.request().toString());
//                try {
//                    NpLog.e("onResponse->response:" + response.getData());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//
//        });

//        new Callback<YCRespData>() {
//            @Override
//            public void onResponse(Call<YCRespData> call, Response<YCRespData> response) {
//                NpLog.e("                                              ");
//                NpLog.e("==============onResponse======================");
//                NpLog.e("onResponse->call:" + call.request().toString());
//                try {
//                    NpLog.e("onResponse->response:" + response.body());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<YCRespData> call, Throwable t) {
//                NpLog.e("                                             ");
//                NpLog.e("==============onFailure======================");
//                NpLog.e("onFailure->call:" + call.request().toString());
//                NpLog.e("onFailure->t:" + t.getMessage());
//            }
//        });
//        new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                NpLog.e("                                              ");
//                NpLog.e("==============onResponse======================");
//                NpLog.e("onResponse->call:" + call.request().toString());
//                try {
//                    NpLog.e("onResponse->response:" + response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                NpLog.e("                                             ");
//                NpLog.e("==============onFailure======================");
//                NpLog.e("onFailure->call:" + call.request().toString());
//                NpLog.e("onFailure->t:" + t.getMessage());
//            }
//        });
    }

    /**
     * 接运输任务
     *
     * @param callback
     */
    public void receiveTrsnsTask(YCNetCallback<YCResp> callback) {
        ApiService apiService = mRetrofit.create(ApiService.class);
        apiService.getqrcode().enqueue(callback);
    }


}
