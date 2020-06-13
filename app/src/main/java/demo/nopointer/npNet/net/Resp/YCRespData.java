package demo.nopointer.npNet.net.Resp;

/**
 * Created by nopointer on 2018/8/7.
 */

public class YCRespData<T> extends YCResp {

    public YCRespData(int errorCode, String message) {
        super(errorCode, message);
    }

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "YCRespData{" +
                "data=" + data +
                "} " + super.toString();
    }
}
