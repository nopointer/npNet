package demo.nopointer.npNet.net;

import demo.nopointer.npNet.net.Resp.YCResp;
import npNet.nopointer.core.NpCall;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {


    /*接运输任务*/
    @Headers("BaseUrlName:" + NetManager.replacePathMLB2Name)
//    @FormUrlEncoded
    @POST("weixin/get_mac_ticket")
//    @POST("weixin/getqrcode")
    NpCall<YCResp> getqrcode();


}


