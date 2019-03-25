package com.demo;

import com.demo.server.OnServerChangeListener;
import com.demo.server.ServerPresenter;
import com.demo.utils.SendMsg;
import com.facebook.react.ReactActivity;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends ReactActivity implements OnServerChangeListener {
    private ServerPresenter serverPresenter;
    private ReactContext reactContext;
	private static final String TAG = "MainActivity";
//    public MainActivity(ReactApplicationContext reactContext) {
//        super();
//        //给上下文对象赋值
//        SendMsg.myContext=reactContext;
//    }
    /**
     *
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 程序创建的时候执行
        Log.i(TAG, "程序MainActivity入口");
//        reactContext = (ReactContext) getApplicationContext();
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
        Log.i(TAG, "服务器开始运行了，IPdeAddress: " + ipAddress);
//        SendMsg.myContext= (ReactContext) getApplicationContext();
//        new SendMsg().send("testData","收到客户端发来的消息了");
    }

    @Override
    public void onServerStopped() {
        Log.i(TAG, "服务器停止了");
    }

    @Override
    public void onServerError(String errorMessage) {
        Log.i(TAG, "服务器出错了");
    }


    @Override
    protected void onDestroy() {
        //退出当前Activity时被调用,调用之后Activity就结束了
        super.onDestroy();
        Log.i(TAG, "Activity销毁了");
		serverPresenter.stopServer(MainActivity.this);
//         serverPresenter.unregister(this);
//         serverPresenter = null;
    }
}


