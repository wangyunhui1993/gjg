package com.demo.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.demo.common.Constants;
import com.demo.common.NetUtils;
//import com.demo.handler.DownloadFileHandler;
//import com.demo.handler.DownloadImageHandler;
import com.demo.handler.JsonHandler;
//import com.demo.handler.UploadFileHandler;
import com.demo.utils.SendMsg;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.yanzhenjie.andserver.AndServer;
import com.yanzhenjie.andserver.Server;
import com.yanzhenjie.andserver.filter.HttpCacheFilter;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;


import android.widget.Toast;

/**
 * 作者：leavesC
 * 时间：2018/4/5 16:30
 * 描述：https://github.com/leavesC/AndroidServer
 * https://www.jianshu.com/u/9df45b87cfdf
 */
public class ServerService extends Service {

    private Server server;
    private ReactContext reactContext;
    private static final String TAG = "ServerService";


    @Override
    public void onCreate() {
        Log.i(TAG, "服务器onCreate - Thread ID = " + Thread.currentThread().getId());
//         super.onCreate();

			server = AndServer.serverBuilder()
                .inetAddress(NetUtils.getLocalIPAddress())  //服务器要监听的网络地址
                .port(Constants.PORT_SERVER) //服务器要监听的端口
                .timeout(10, TimeUnit.SECONDS) //Socket超时时间
//                .registerHandler(Constants.GET_FILE, new DownloadFileHandler()) //注册一个文件下载接口
//                .registerHandler(Constants.GET_IMAGE, new DownloadImageHandler()) //注册一个图片下载接口
//                .registerHandler(Constants.UP_LOAD, new UploadFileHandler()) //注册一个图片下载接口
                .registerHandler(Constants.POST_JSON, new JsonHandler((ReactApplicationContext) reactContext)) //注册一个Post Json接口
                .filter(new HttpCacheFilter()) //开启缓存支持
                .listener(new Server.ServerListener() {  //服务器监听接口
                    @Override
                    public void onStarted() {
                        String hostAddress = server.getInetAddress().getHostAddress();
                        Log.i(TAG, "服务器监听 : onStarted" + hostAddress);
                        ServerPresenter.onServerStarted(ServerService.this, hostAddress);
                    }

                    @Override
                    public void onStopped() {
                        Log.i(TAG, "服务器监听 : onStopped" );
                        ServerPresenter.onServerStopped(ServerService.this);
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.i(TAG, "服务器监听 : onStopped" + e.getMessage() );
                        ServerPresenter.onServerError(ServerService.this, e.getMessage());
                    }
                })
                .build();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"服务器状态：服务器启动");
        startServer();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"服务器状态：服务器已销毁");
        super.onDestroy();
        stopServer();
    }
	
    public void startServer() {
        //如果Server已启动则不再重复启动，此时只是向外发布已启动的状态
        Log.i(TAG,"服务器状态：服务器已启动");
		Log.i(TAG,"服务器状态isRunning："+server.isRunning());
        if (server.isRunning()) {
			Log.i(TAG,"服务器状态：服务器正在运行着");
            InetAddress inetAddress = server.getInetAddress();
            if (inetAddress != null) {
                String hostAddress = inetAddress.getHostAddress();
                if (!TextUtils.isEmpty(hostAddress)) {
                    ServerPresenter.onServerStarted(ServerService.this, hostAddress);
                }
            }
        } else {
			Log.i(TAG,"服务器状态：服务器没在运行着");
            server.startup();
        }
    }

    private void stopServer() {
        Log.i(TAG,"服务器状态：服务器已停止");
        if (server != null && server.isRunning()) {
            server.shutdown();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
