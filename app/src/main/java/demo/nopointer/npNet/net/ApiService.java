package demo.nopointer.npNet.net;

import demo.nopointer.npNet.net.Resp.YCResp;
import npNet.nopointer.core.NpCall;
import retrofit2.http.POST;

public interface ApiService {


    /*接运输任务*/
    @POST("task/carTypeList")
    NpCall<YCResp> receiveTrsnsTask( );






}


