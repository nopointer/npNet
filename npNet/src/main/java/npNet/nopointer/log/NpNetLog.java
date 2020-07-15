package npNet.nopointer.log;

import android.text.TextUtils;
import android.util.Log;

public class NpNetLog {


    /**
     * 是否允许log
     */
    public static boolean enableLog = true;


    /**
     * 是否允许lig的Log
     */
    public static boolean enableLibLog = false;

    public static void logLibBleLog(String message) {
        if (!enableLibLog) return;
        Log.e("NpNetLog", message);
    }

    public static void log(String message) {
        if (!enableLog) return;
        if (mNpBleLogPrinter != null) {
            log(mNpBleLogPrinter.initTag(), message);
        } else {
            log("NpNetLog", message);
        }
    }

    public static void log(String tag, String message) {
        if (TextUtils.isEmpty(tag)) {
            tag = "NpNetLog";
        }
        if (mNpBleLogPrinter == null) {
            Log.e(tag, message);
        } else {
            mNpBleLogPrinter.onLogPrint(tag, message);
        }
    }

    private static NpBleLogPrinter mNpBleLogPrinter;

    public static void setNpBleLogPrinter(NpBleLogPrinter npBleLogPrinter) {
        mNpBleLogPrinter = npBleLogPrinter;
    }

    public static interface NpBleLogPrinter {
        void onLogPrint(String message);

        void onLogPrint(String tag, String message);

        String initTag();
    }


}