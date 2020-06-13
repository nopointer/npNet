package demo.nopointer.npNet;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDex;


public class MainApplication extends Application {


    private static final int NOTIFICATION_TITLE_TYPE = 9;
    private static final int NOTIFICATION_CONTENT_TYPE = 10;


    public static MainApplication mainApplication = null;

    private Handler handler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        mainApplication = this;


    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static MainApplication getMainApplication() {
        return mainApplication;
    }





}
