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


}
