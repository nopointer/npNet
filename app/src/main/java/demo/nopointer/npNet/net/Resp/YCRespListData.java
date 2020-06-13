package demo.nopointer.npNet.net.Resp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nopointer on 2018/8/7.
 */

public class YCRespListData<T> extends YCResp {


    public YCRespListData(int errorCode, String message) {
        super(errorCode, message);
    }

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "YCRespListData{" +
                "data=" + data +
                '}';
    }
}
