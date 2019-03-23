package com.demo;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.List;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

import java.util.ArrayList;
/**
 * Created by neo on 2019/3/6.
 * 现在说另一个类ReactPackage,其实该类的基本作用就是把继承ReactContextBaseJavaModule类的方法注册到JS里
 */

public class TakeViewPackage implements ReactPackage {
//    其中就把TakeViewModule对象添加到modules这个list上。
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Arrays.<NativeModule>asList(new TakeViewModule(reactContext));
    }
//   返回Collections.emptyList();
	// @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }
//  返回Collections.emptyList();
    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}


