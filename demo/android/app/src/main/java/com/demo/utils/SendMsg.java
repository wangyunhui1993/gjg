package com.demo.utils;
 
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
 
/**
 * Created by Administrator on 2016/10/30.
 */
 
public class SendMsg {
 
     //定义上下文对象
    public static ReactContext myContext;

//    //定义发送事件的函数
//    public static void sendEvent(ReactContext reactContext, String eventName, Object params)
//    {
//
//        reactContext
//                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
//                .emit(eventName,params);
//    }
//
//    public  void send(final String eventName,@Nullable final WritableMap params)
//    {
//        //在该方法中开启线程，并且延迟3秒，然后向JavaScript端发送事件。
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
////                try {
////                    Thread.sleep(3000);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
////
//                Log.i("server","监听器：测试发送消息");
////               //发送事件,事件名为EventName
//                WritableMap et= Arguments.createMap();
//                Log.i("server","监听器：打印对象"+et);
//                sendEvent(myContext,eventName,params);
//
//
//            }
//        }).start();
//
//    }



    //定义发送事件的函数
    public static void sendEvent(ReactContext reactContext, String eventName, String value)
    {

        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName,value);
    }

    public  void send(final String eventName, final String value)
    {
        //在该方法中开启线程，并且延迟3秒，然后向JavaScript端发送事件。
        new Thread(new Runnable() {
            @Override
            public void run() {

//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
                Log.i("server","监听器：测试发送消息");
//               //发送事件,事件名为EventName
                WritableMap et= Arguments.createMap();
                Log.i("server","监听器：打印对象"+et);
                sendEvent(myContext,eventName,value);


            }
        }).start();

    }


 
 
}
