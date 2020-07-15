package demo.nopointer.npNet;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import demo.nopointer.R;
import demo.nopointer.npNet.net.NetManager;
import demo.nopointer.npNet.net.Resp.YCResp;
import demo.nopointer.npNet.net.impl.YCNetCallback;
import npNet.nopointer.core.NpCall;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        NetManager.getInstance().login();

        findViewById(R.id.test_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetManager.getInstance().receiveTrsnsTask("1", new YCNetCallback<YCResp>() {
                    @Override
                    public void onSuccess(NpCall<YCResp> call, YCResp response) {

                    }
                });
//                NetManager.getInstance().login();
//                NetManager.getInstance().login();
//                NetManager.getInstance().login();
//                NetManager.getInstance().login();
//                NetManager.getInstance().login();
//                NetManager.getInstance().login();
//                NetManager.getInstance().login();
//                NetManager.getInstance().login();
//                NetManager.getInstance().login();
//                NetManager.getInstance().login();
//                NetManager.getInstance().login();
            }
        });

    }


}
