package com.demo;


import android.util.Log;

import com.demo.utils.SendMsg;
import com.demo.utils.TestClass;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;


import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.io.File;
import java.util.Map;
import java.util.HashMap;

import com.demo.server.ServerService;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import android_serialport_api.SerialPort;


/**
 * Created by neo on 2019/03/11.
 * 原生的代码，之后与JS交互
 */

public class TakeViewModule extends ReactContextBaseJavaModule {
	private ReactContext reactContext;
	private SerialPort serialPort;
    public TakeViewModule(ReactApplicationContext reactContext) {
        super(reactContext);
        //给上下文对象赋值
		SendMsg.myContext=reactContext;
    }
   
    /**
     * return
     * string
     * 这个名字在JavaScript端标记这个模块
     * 这样就可以在JavaScript中通过React.NativeModules.TakeViewManager访问到这个模块
     * */
    @Override
    public String getName() {
        return "TakeViewManager";
    }
	 /**
	 * 该方法就是给js使用
	 * Java方法需要使用注解@ReactMethod。
	 * 方法的返回类型必须为void。
	 * 测试安卓的回调方法
	 * React Native的跨语言访问是异步进行的，所以想要给JavaScript返回一个值的唯一办法是使用回调函数或者发送事件
	 * */

	@ReactMethod
	public void addEventCeshi() {
// 		ServerService myService = new ServerService();
// 		myService.startServer();
	    // 1.处理业务逻辑...
//		WritableMap params = Arguments.createMap();
//		params.putString("data", "AAAA");
		Log.i("server","监听器：发送消息");
//		Object aa=new TestClass();

		try{
			serialPort = new SerialPort(new File("/dev/ttyAMA0"),9600,0);
			serialPort.sendHex("3A30313035303530304646303046360D0A");
		}catch (Exception e){
			e.printStackTrace();
		}
//		new SendMsg().send("testData", (WritableMap) aa);
//		reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("testData", "11111");
		Log.i("server","监听器：发送消息");
	}





//	@ReactMethod
//	public void addEventCeshi(String name, Callback callback) {
//// 		ServerService myService = new ServerService();
//// 		myService.startServer();
//		// 1.处理业务逻辑...
////		WritableMap params = Arguments.createMap();
////		params.putString("data", "AAAA");
//		Log.i("server","监听器：发送消息");
//		reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("testData", "11111");
//		Log.i("server","监听器：发送消息");
//		String result = "处理结果：" + name;
//		// 2.回调RN,即将处理结果返回给RN
//		callback.invoke(true,result);
//	}
}
