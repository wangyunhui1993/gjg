package com.myapp;

import android.app.Application;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import org.pgsqlite.SQLitePluginPackage;
import com.learnium.RNDeviceInfo.RNDeviceInfo;
import com.github.kevinejohn.keyevent.KeyEventPackage;
import com.github.yamill.orientation.OrientationPackage; 
import org.devio.rn.splashscreen.SplashScreenReactPackage;
import com.rnimmersive.RNImmersivePackage;

import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override
    public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
			new SQLitePluginPackage(),
					
					new RNDeviceInfo(),
					new KeyEventPackage(),
					new OrientationPackage(),
					new SplashScreenReactPackage(),
					new RNImmersivePackage(),
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
    SoLoader.init(this, /* native exopackage */ false);
  }
}
