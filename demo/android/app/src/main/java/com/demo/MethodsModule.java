package com.demo;


import android.app.Activity;
import android.util.Log;

import com.demo.server.DBServiceImpl;
import com.demo.utils.SendMsg;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;


import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.io.File;
import java.util.List;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import android_serialport_api.SerialPort;
import entity.Stu;


/**
 * Created by neo on 2019/03/11.
 * 原生的代码，之后与JS交互
 */

public class MethodsModule extends ReactContextBaseJavaModule {
	private ReactContext myreactContext;
	private SerialPort serialPort;
	private DBServiceImpl dbService;
    public MethodsModule(ReactApplicationContext reactContext) {
        super(reactContext);
        //给上下文对象赋值
		SendMsg.myContext=reactContext;
		myreactContext = reactContext;
    }
   
    /**
     * return
     * string
     * 这个名字在JavaScript端标记这个模块
     * 这样就可以在JavaScript中通过React.NativeModules.TakeViewManager访问到这个模块
     * */
    @Override
    public String getName() {
        return "MethodsManager";
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





	@ReactMethod
	public void addEventCallback(String name, Callback callback) {
//		reactContext = (ReactContext) getApplicationContext();
        dbService = new DBServiceImpl(myreactContext);
        //新增
            dbService.add("xiaozhang","001");
            dbService.add2("xiaoliu","002");
        //查询
        List<Stu> stuArrayList = dbService.select1(0,10,"");
        Log.i("MethodsModule","查询到数据: "+stuArrayList.toString());
        //修改源数据
        stuArrayList.get(0).setSnumber("003");
        Log.i("MethodsModule","修改源数据: "+stuArrayList.toString());
        dbService.update1(stuArrayList);
        //修改后再次查询一次
        List<Stu> stuArrayList2 = dbService.select1(0,10,"");
        Log.i("MethodsModule","修改数据后,查询到数据: "+stuArrayList2.toString());
        //删除数据
        dbService.delete1("1");
        //查询
        List<Stu> stuArrayList3 = dbService.select1(0,10,"");
        Log.i("MethodsModule","删除数据后,查询到数据: "+stuArrayList3.toString());


// 		ServerService myService = new ServerService();
// 		myService.startServer();
		// 1.处理业务逻辑...
//		WritableMap params = Arguments.createMap();
//		params.putString("data", "AAAA");
		Log.i("MethodsModule","监听器：发送消息");
//		reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("testData", "11111");
		Log.i("MethodsModule","监听器：发送消息");
		String result = "处理结果：" + name;
		// 2.回调RN,即将处理结果返回给RN
		callback.invoke(true,result);
	}
}
