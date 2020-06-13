package npNet.nopointer.core.parser;

import android.support.annotation.Nullable;

import org.json.JSONObject;

import java.lang.reflect.Type;

import npNet.nopointer.utils.NpUtils;
import okhttp3.ResponseBody;
import retrofit2.Converter;


/**
 * Gson基本解析基类
 *
 * @param <T>
 */
public abstract class NpBaseGsonConverter<T> implements Converter<ResponseBody, T> {
    protected final Type type;
    protected final Class<?> rawType;

    protected NpBaseGsonConverter(Type type, Class<?> rawType) {
        this.type = type;
        this.rawType = rawType;
    }

    /**
     * 是否为空的jsonObject对象 {}
     **/
    protected static boolean isEmptyJSON(@Nullable Object data) {
        return data instanceof JSONObject && ((JSONObject) data).length() == 0;
    }

    /**
     * data 如{"msg": "xxx","code": xxx,"data": xxx}
     * 解析基础装箱类型的参数:
     * String|Boolean|Integer|Long|Short|Double|Float|Byte
     * <p>
     * 子类可重载扩展
     * <p>
     * if (data == null) return null;
     * <p>
     * 重新定义泛型V ，不限制其必须为T，更灵活。
     */
    @Nullable
    @SuppressWarnings("unchecked")
    protected static <V> V convertBaseType(@Nullable Object data, Class<?> baseType) {
        NpUtils.checkNotNull(baseType == null, "baseType==null");
        if (data == null) {
            return null;
        }
        //如果是String 直接返回
        if (String.class == baseType) {
            return (V) String.valueOf(data);
        }
        if (Boolean.class == baseType && data instanceof Boolean) {
            return (V) data;
        }
        //检测是否为装箱类型
        if (!(data instanceof Number)) {
            return null;
        }
        //防止JSON不是引用我们想要的类型
        Number number = (Number) data;
        //赋值时自动装箱
        Number value = null;
        if (Integer.class == baseType) {
            value = number.intValue();
        } else if (Long.class == baseType) {
            value = number.longValue();
        } else if (Short.class == baseType) {
            value = number.shortValue();
        } else if (Double.class == baseType) {
            value = number.doubleValue();
        } else if (Float.class == baseType) {
            value = number.floatValue();
        } else if (Byte.class == baseType) {
            value = number.byteValue();
        }
        return (V) value;
    }
}