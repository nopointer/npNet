package demo.nopointer.npNet.net.parser;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import demo.nopointer.npNet.net.Resp.YCResp;
import npNet.nopointer.core.error.NpHttpError;
import npNet.nopointer.core.error.NpHttpWithJsonDataButNotRealError;
import npNet.nopointer.core.parser.NpBaseGsonConverter;
import npNet.nopointer.log.NpNetLog;
import okhttp3.ResponseBody;

public final class GsonResponseBodyConverter<T> extends NpBaseGsonConverter<T> {
    private final Gson gson;

    GsonResponseBodyConverter(Gson gson, Type type) {
        super(type, $Gson$Types.getRawType(type));
        this.gson = gson;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T convert(@NonNull ResponseBody value) throws IOException {
        String responseStr = value.string();
        NpNetLog.log("responseStr=>" + responseStr);
        try {
            JSONObject jsonObject = new JSONObject(responseStr);

            if (jsonObject.isNull("errorCode")&&(jsonObject.isNull("msg")||jsonObject.isNull("message"))){
                //数据是Json，但是不符合通用json数据结构
                NpNetLog.log("数据是Json，但是不符合通用json数据结构,数据需要从其他接口去取");
                NpHttpWithJsonDataButNotRealError jsonDataButNotRealError =new NpHttpWithJsonDataButNotRealError();
                jsonDataButNotRealError.setJsonString(responseStr);
                throw jsonDataButNotRealError;
            }
            final int code = jsonObject.getInt("errorCode");
            String msg = "no msg";
            if (!jsonObject.isNull("msg")) {
                msg = jsonObject.getString("msg");
            }
            if (!jsonObject.isNull("message")) {
                msg = jsonObject.getString("message");
            }

            if (code != 0) {
                NpNetLog.log("code非0 ,抛出异常" + code);
                //如果data不等于空的话
                if (!jsonObject.isNull("data")) {
                    throw new NpHttpError(200, code, msg,responseStr);
                }
                throw new NpHttpError(200, code, msg);
            }
            if (YCResp.class == rawType) {
                return (T) new YCResp(code, msg);
            }

            //如果data不等于空的话
            if (!jsonObject.isNull("data")) {
                Object data = jsonObject.get("data");
                if (!isEmptyJSON(data)) {
                    T t = convertBaseType(data, rawType);
                    if (t != null) {
                        return t;
                    }
                    t = gson.fromJson(data.toString(), type);
                    if (t != null) {
                        //防止线上接口修改导致反序列化失败奔溃
                        return t;
                    }
                }
            }
            return (T) type;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new NpHttpError(200, 0, e.getMessage());
        }
    }
}