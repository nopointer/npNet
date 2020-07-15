package demo.nopointer.npNet.net;

import demo.nopointer.npNet.net.Resp.YCResp;
import demo.nopointer.npNet.net.Resp.YCRespData;
import demo.nopointer.npNet.net.Resp.YCRespListData;
import demo.nopointer.npNet.net.entity.LoginResult;
import demo.nopointer.npNet.net.entity.UserEntity;
import npNet.nopointer.core.NpCall;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {


    /*接运输任务*/
    @POST("task/receiveTrsnsTask")
    @FormUrlEncoded
    NpCall<YCResp> receiveTrsnsTask(@Field("transId") String transId);






}


