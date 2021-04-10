package npNet.nopointer.log;

import android.text.TextUtils;
import android.util.Log;

public class NpNetLog {


    /**
     * 是否允许log
     */
    public static boolean enaLog = true;


    public static void log(String message) {
        if (!enaLog) return;
        if (mNpNetLogPrinter != null) {
            log(mNpNetLogPrinter.initTag(), message);
        } else {
            log("NpNetLog", message);
        }
    }

    public static void log(String tag, String message) {
        if (TextUtils.isEmpty(tag)) {
            tag = "NpNetLog";
        }
        if (mNpNetLogPrinter == null) {
            StackTraceElement caller = getCallerStackTraceElement();
            message = "[" + getCallPathAndLineNumber(caller) + "]：" + message;
            Log.e(tag, message);
        } else {
            mNpNetLogPrinter.onLogPrint(tag, message);
        }
    }

    private static NpNetLogPrinter mNpNetLogPrinter;

    public static void setNpNetLogPrinter(NpNetLogPrinter NpNetLogPrinter) {
        mNpNetLogPrinter = NpNetLogPrinter;
    }

    public static interface NpNetLogPrinter {
        void onLogPrint(String message);

        void onLogPrint(String tag, String message);

        String initTag();
    }


    /**
     * 获取调用路径和行号
     *
     * @return
     */
    private static String getCallPathAndLineNumber(StackTraceElement caller) {
        String result = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        result = String.format(result, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        return result;
    }


    public static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }

}
