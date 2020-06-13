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


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        NetManager.getInstance().login();

        findViewById(R.id.test_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetManager.getInstance().login();
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
