package com.demo;

import com.demo.server.OnServerChangeListener;
import com.demo.server.ServerPresenter;
import com.facebook.react.ReactActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends ReactActivity implements OnServerChangeListener {
    private ServerPresenter serverPresenter;
    /**
     *
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 程序创建的时候执行
        Log.i("MainActivity", "程序MainActivity入口");
        super.onCreate(savedInstanceState);
        serverPresenter = new ServerPresenter(this, this);
        serverPresenter.startServer(MainActivity.this);
    }


    @Override
    protected String getMainComponentName() {
        return "demo";
    }

    @Override
    public void onServerStarted(String ipAddress) {
        Log.i("服务器", "服务器开始运行了，IPdeAddress: " + ipAddress);
    }
    @Override
    protected void onDestroy() {
        //退出当前Activity时被调用,调用之后Activity就结束了
        super.onDestroy();
        Log.i("服务器", "Activity销毁了");
        serverPresenter.unregister(this);
        serverPresenter = null;
    }
    @Override
    public void onServerStopped() {
        Log.i("服务器", "服务器停止了");
    }

    @Override
    public void onServerError(String errorMessage) {
        Log.i("服务器", "服务器出错了");
    }
}
