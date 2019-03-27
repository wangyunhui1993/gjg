package com.demo.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.demo.MainActivity;

/**
 * 作者：leavesC
 * 时间：2018/4/5 16:30
 * 描述：https://github.com/leavesC/AndroidServer
 * https://www.jianshu.com/u/9df45b87cfdf
 */
public class ServerPresenter {

    private static final String ACTION_SERVER_CHANGE = "com.leavesc.androidserver.action_server_change";

    private static final String KEY_SERVER_STATE = "serverState";

    private static final String KEY_SERVER_MESSAGE = "serverMessage";

    private static final int VALUE_STARTED = 100;

    private static final int VALUE_STOPPED = 200;

    private static final int VALUE_ERROR = 300;
	private static final String TAG = "ServerPresenter";
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int state = intent.getIntExtra(KEY_SERVER_STATE, 0);
            switch (state) {
                case VALUE_STARTED: {
                    if (serverChangeListener != null) {
                        serverChangeListener.onServerStarted(intent.getStringExtra(KEY_SERVER_MESSAGE));
                    }
                    break;
                }
                case VALUE_STOPPED: {
                    if (serverChangeListener != null) {
                        serverChangeListener.onServerStopped();
                    }
                    break;
                }
                case VALUE_ERROR: {
                    if (serverChangeListener != null) {
                        serverChangeListener.onServerError(intent.getStringExtra(KEY_SERVER_MESSAGE));
                    }
                    break;
                }
            }
        }
    };

    private OnServerChangeListener serverChangeListener;

    public ServerPresenter(MainActivity context, MainActivity serverChangeListener) {
        context.registerReceiver(broadcastReceiver, new IntentFilter(ACTION_SERVER_CHANGE));
        this.serverChangeListener = serverChangeListener;
    }

    public void startServer(Context context) {
        Log.i(TAG,"动作：启动服务器");
        context.startService(new Intent(context, ServerService.class));
    }

    public void stopServer(Context context) {
        Log.i(TAG,"动作：停止服务器");
        context.stopService(new Intent(context, ServerService.class));
    }

    public void unregister(Context context) { 
        Log.i(TAG,"unregister：服务器unregister");
        context.unregisterReceiver(broadcastReceiver);
        this.serverChangeListener = null;
    }

    public static void onServerStarted(Context context, String ipAddress) {
        Log.i(TAG,"onServerStarted：服务器onServerStarted");
        Intent intent = new Intent(ACTION_SERVER_CHANGE);
        intent.putExtra(KEY_SERVER_STATE, VALUE_STARTED);
        intent.putExtra(KEY_SERVER_MESSAGE, ipAddress);
        context.sendBroadcast(intent);
    }

    public static void onServerStopped(Context context) {
        Log.i(TAG,"onServerStopped：服务器onServerStopped");
        Intent intent = new Intent(ACTION_SERVER_CHANGE);
        intent.putExtra(KEY_SERVER_STATE, VALUE_STOPPED);
        context.sendBroadcast(intent);
    }

    public static void onServerError(Context context, String message) {
        Log.i(TAG,"onServerError：服务器onServerError");
        Intent intent = new Intent(ACTION_SERVER_CHANGE);
        intent.putExtra(KEY_SERVER_STATE, VALUE_ERROR);
        intent.putExtra(KEY_SERVER_MESSAGE, message);
        context.sendBroadcast(intent);
    }

}
