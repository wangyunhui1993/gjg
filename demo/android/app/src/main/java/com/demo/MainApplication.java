package com.demo;

import android.app.Application;

import com.demo.server.ServerService;
import com.demo.server.TestOneService;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.util.Arrays;
import java.util.List;

import android.content.Intent;
import com.demo.server.ServerPresenter;

public class MainApplication extends Application implements ReactApplication {

  private ServerPresenter serverPresenter;



  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override
    public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
              new TakeViewPackage(),
          new MainReactPackage()
      );
    }

    @Override
    protected String getJSMainModuleName() {
      return "index";
    }
  };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();
//    serverPresenter.startServer(MainApplication.this);
//    ServerService s = new ServerService();
//    s.onCreate();
//    TestOneService s = new TestOneService();
//    s.aa();
    //    Toast.makeText(MainApplication.this, "显示Toast", Toast.LENGTH_SHORT).show();
    SoLoader.init(this, /* native exopackage */ false);
  }
}
