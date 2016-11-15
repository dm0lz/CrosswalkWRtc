package com.example.olivier.crosswalkwrtc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.net.http.SslError;
import android.os.Bundle;
import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkView;
import org.xwalk.core.XWalkResourceClient;
import android.webkit.ValueCallback;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private XWalkView xWalkWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadXWalk();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(MainActivity.this, SplashActivity.class);
        startActivity(intent);
    }

    public void loadXWalk(){
        xWalkWebView = (XWalkView) findViewById(R.id.xwalkWebView);
        xWalkWebView.setResourceClient(new MyResourceClient(xWalkWebView));
        xWalkWebView.load("https://192.168.10.100:8443", null);
        XWalkPreferences.setValue(XWalkPreferences.REMOTE_DEBUGGING, true);
    }

    class MyResourceClient extends XWalkResourceClient {
        public MyResourceClient(XWalkView view) {
            super(view);
        }
        @Override
        public void onReceivedSslError(XWalkView view, ValueCallback<Boolean> callback, SslError error) {
            callback.onReceiveValue(true);
        }
    }

}
