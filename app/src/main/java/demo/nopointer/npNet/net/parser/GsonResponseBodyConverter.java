package demo.nopointer.npNet.net.parser;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import demo.nopointer.npNet.net.Resp.YCResp;
import npNet.nopointer.core.error.NpHttpError;
import npNet.nopointer.core.parser.NpBaseGsonConverter;
import npNet.nopointer.utils.log.LogUtil;
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
        LogUtil.e("responseStr=>" + responseStr);
        try {
            JSONObject jsonObject = new JSONObject(responseStr);
            final int code = jsonObject.getInt("errorCode");
            String msg = "no msg";
            if (!jsonObject.isNull("msg")) {
                msg = jsonObject.getString("msg");
            }
            if (!jsonObject.isNull("message")) {
                msg = jsonObject.getString("message");
            }

            if (code != 0) {
                LogUtil.e("code非0 ,抛出异常" + code);
                throw new NpHttpError(200, code, msg);
            }
            if (YCResp.class == rawType) {
                return (T) new YCResp(code, msg);
            }
            LogUtil.e("rawType===>" + rawType);
            LogUtil.e("type===>" + type);


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
            throw new NpHttpError(200, 0, e.getMessage());
        }
    }
}