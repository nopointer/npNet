package npNet.nopointer.core.error;

/**
 * 这个并不是真的错误，而是解析的时候给那些不是通用数据使用的一个场景
 */
public class NpHttpWithJsonDataButNotRealError extends RuntimeException {

    private String jsonString =null;

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }
}
